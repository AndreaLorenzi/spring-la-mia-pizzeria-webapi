package com.experis.course.springlamiapizzeriacrud.service;

import com.experis.course.springlamiapizzeriacrud.exceptions.DiscountNotFoundException;
import com.experis.course.springlamiapizzeriacrud.exceptions.PizzaNotFoundException;
import com.experis.course.springlamiapizzeriacrud.model.Discount;
import com.experis.course.springlamiapizzeriacrud.model.Pizza;
import com.experis.course.springlamiapizzeriacrud.repository.DiscountRepository;
import com.experis.course.springlamiapizzeriacrud.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DiscountService {
    @Autowired
    PizzaRepository pizzaRepository;
    @Autowired
    DiscountRepository discountRepository;

    public Discount createNewDiscount(Integer pizzaId) throws PizzaNotFoundException {
        Pizza pizza = pizzaRepository.findById(pizzaId).orElseThrow(() -> new PizzaNotFoundException("Pizza with id " + pizzaId + " not found"));
        Discount discount = new Discount();
        discount.setStartDate(LocalDate.now());
        discount.setFinishDate(LocalDate.now().plusWeeks(1));
        discount.setPizza(pizza);
        return discount;
    }

    public Discount saveDiscount(Discount discount) {
        return discountRepository.save(discount);
    }

    public Discount getDiscount(Integer id) throws DiscountNotFoundException {
        return discountRepository.findById(id).orElseThrow(() -> new DiscountNotFoundException("Discount with id " + id + " not found"));
    }
}
