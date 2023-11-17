package com.experis.course.springlamiapizzeriacrud.controller;

import com.experis.course.springlamiapizzeriacrud.exceptions.DiscountNotFoundException;
import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Discount;
import com.experis.course.springlamiapizzeriacrud.repository.DiscountRepository;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import com.experis.course.springlamiapizzeriacrud.service.DiscountService;
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
@RequestMapping("/discounts")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @GetMapping("/create")
    public String create(@RequestParam Integer pizzaId, Model model) {
        try {
            model.addAttribute("discount", discountService.createNewDiscount(pizzaId));
            return "discounts/form";
        } catch (PizzaNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/create")
    public String doCreate(@Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "discounts/form";
        }
        Discount saveDiscount = discountService.saveDiscount(formDiscount);
        return "redirect:/pizzas/show/" + formDiscount.getPizza().getId();
    }


    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        try {
            Discount discount = discountService.getDiscount(id);
            model.addAttribute("discount", discount);
            return "discounts/form";
        } catch (DiscountNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }

    @PostMapping("/edit/{id}")
    public String doEdit(@PathVariable Integer id, @Valid @ModelAttribute("discount") Discount formDiscount, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "discounts/form";
        }
        Discount savedDiscount = discountService.saveDiscount(formDiscount);
        return "redirect:/pizzas/show/" + formDiscount.getPizza().getId();
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        // cancello il discount
        try {
            Discount discountToDelete = discountService.getDiscount(id);
            discountService.deleteDiscount(discountToDelete);
            redirectAttributes.addFlashAttribute("message", " Discount deleted!");
            return "redirect:/pizzas/show/" + discountToDelete.getPizza().getId();
        } catch (DiscountNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        //e faccio la redirect

    }
}
