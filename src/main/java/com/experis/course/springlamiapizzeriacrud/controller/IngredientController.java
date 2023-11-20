package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.exceptions.IngredientNameUniqueException;
import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Ingredient;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.service.IngredientService;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/ingredients")
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("ingredientList", ingredientService.getAll());
        model.addAttribute("ingredientObj", new Ingredient());
        return "ingredients/index";
    }

    @PostMapping
    public String doSave(@Valid @ModelAttribute("ingredientObj") Ingredient formIngredient, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("ingredientList", ingredientService.getAll());
            return "ingredients/index";
        }
        try {
            ingredientService.save(formIngredient);
            return "redirect:/ingredients";
        } catch (IngredientNameUniqueException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A ingredient with name " + e.getMessage() + "already exist");
        }

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            Ingredient ingredientToDelete = ingredientService.getIngredientById(id);
            ingredientService.deleteIngredient(id);
            redirectAttributes.addFlashAttribute("message", "The " + ingredientToDelete.getName() + " deleted!");
            return "redirect:/ingredients";
        } catch (IngredientNameUniqueException e) {
            // Gestire l'errore appropriatamente, ad esempio reindirizzando a una pagina di errore
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());

        }
    }
}
