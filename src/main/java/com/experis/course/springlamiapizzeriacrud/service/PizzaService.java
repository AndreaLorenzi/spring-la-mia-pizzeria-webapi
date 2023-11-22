package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    public List<Pizza> getPizzaList(Optional<String> search) {

        if (search.isPresent()) {
            // se il parametro di ricerca è presente filtro la lista dei libri
            return
                    pizzaRepository.findByNameContainingIgnoreCase(search.get());
        } else {
            // altrimenti prendo tutti i libri non filtrati
            // bookRepository recupera da database la lista di tutti i libri
            return pizzaRepository.findAll();
        }
    }

    public List<Pizza> getPizzaList() {
        return pizzaRepository.findAll();
    }

    public Pizza getPizzaById(Integer id) throws PizzaNotFoundException {
        Optional<Pizza> result = pizzaRepository.findById(id);
        // verifico se il risultato è presente
        if (result.isPresent()) {
            return result.get();
        } else {
            // se non ho trovato il libro sollevo un'eccezione
            throw new PizzaNotFoundException("pizza with id " + id + " not found");
        }
    }

    public Pizza createPizza(Pizza pizza) throws PizzaNotFoundException {
        pizza.setId(null);
        try {
            return pizzaRepository.save(pizza);
        } catch (RuntimeException e) {
            throw new PizzaNotFoundException(pizza.getName());
        }
    }

    public Pizza editPizza(Pizza pizza) throws PizzaNotFoundException {
        Pizza pizzaToEdit = getPizzaById(pizza.getId());
        pizzaToEdit.setName(pizza.getName());
        pizzaToEdit.setDescription(pizza.getDescription());
        pizzaToEdit.setImageUrl(pizza.getImageUrl());
        pizzaToEdit.setPrice(pizza.getPrice());
        pizzaToEdit.setIngredients(pizza.getIngredients());
        return pizzaRepository.save(pizzaToEdit);
    }

    public void deletePizza(Integer id) {
        pizzaRepository.deleteById(id);
    }

    public Page<Pizza> getPage(Pageable pageable) {
        return pizzaRepository.findAll(pageable);
    }
}
