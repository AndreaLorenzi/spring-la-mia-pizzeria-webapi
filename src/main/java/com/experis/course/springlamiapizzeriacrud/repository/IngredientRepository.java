package com.experis.course.springlamiapizzeriacrud.repository;

import com.experis.course.springlamiapizzeriacrud.model.Ingredient;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    List<Ingredient> findByOrderByName();

    boolean existsByName(String name);
}
