package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(@RequestParam Optional<String> search, Model model) {
        List<Pizza> pizzaList;

        if (search.isPresent()) {
            // se il parametro di ricerca è presente filtro la lista dei libri
            pizzaList = pizzaRepository.findByNameContainingIgnoreCase(search.get());
        } else {
            // altrimenti prendo tutti i libri non filtrati
            // bookRepository recupera da database la lista di tutti i libri
            pizzaList = pizzaRepository.findAll();
        }
        model.addAttribute("pizzaList", pizzaList);
        return "pizzas/home";
    }

    // metodo che mostra i dettagli di un libro preso per id
    @GetMapping("/show/{id}")
    public String show(@PathVariable Integer id, Model model) {
        Optional<Pizza> result = pizzaRepository.findById(id);
        // verifico se il risultato è presente
        if (result.isPresent()) {
            // passo al template l'oggetto Book
            model.addAttribute("pizza", result.get());
            return "pizzas/show";
        } else {
            // se non ho trovato il libro sollevo un'eccezione
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "pizza with id " + id + " not found");
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("pizza", new Pizza());
        return "pizzas/form";
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // ci sono errori, devo ricaricare il form
            return "pizzas/form";
        }
        Pizza savedPizza = null;
        try {
            savedPizza = pizzaRepository.save(formPizza);
        } catch (RuntimeException e) {
            bindingResult.addError(new FieldError("pizza", "name", formPizza.getName(), false, null, null, "Name must be unique"));
            return "pizzas/form";
        }
        return "redirect:/pizzas/show/" + savedPizza.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        // a partire dall'id recupero i dati del libro
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            // aggiungo il book come attributo del Model
            model.addAttribute("pizza", result.get());
            // proseguo a restituire la pagina di modifica
            return "/pizzas/form";
        } else {
            // sollevo un'eccesione con HttpStatus 404
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pizza with id " + id + " not found");
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("pizza") Pizza formPizza,
                         BindingResult bindingResult) {
        // valido il libro
        if (bindingResult.hasErrors()) {
            // se ci sono errori ricarico la pagina col form
            return "/pizzas/form";
        }
        Pizza pizzaToEdit = pizzaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        pizzaToEdit.setName(formPizza.getName());
        pizzaToEdit.setDescription(formPizza.getDescription());
        pizzaToEdit.setImageUrl(formPizza.getImageUrl());
        pizzaToEdit.setPrice(formPizza.getPrice());

        Pizza savedPizza = pizzaRepository.save(pizzaToEdit);
        return "redirect:/pizzas/show/" + savedPizza.getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // recupero il libro con quell'id
        Pizza pizzaToDelete = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        // se esiste lo elimino
        pizzaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("message",
                "pizza " + pizzaToDelete.getName() + " deleted!");
        return "redirect:/pizzas";

    }
}