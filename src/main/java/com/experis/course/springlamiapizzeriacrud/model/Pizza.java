package com.experis.course.springlamiapizzeriacrud.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pizzas")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Name must be not blank")
    @Size(max = 255, message = "lenght must be less than 255")
    private String name;
    @NotBlank(message = "Description must be not blank")
    @Size(max = 255, message = "lenght must be less than 255")
    private String description;
    @NotBlank(message = "imageUrl must be not blank")
    @Size(max = 255, message = "lenght must be less than 255")
    private String imageUrl;
    @NotNull(message = "Price must not be null")
    @Min(value = 1, message = "Price must be greater than 0")
    @Max(value = 50, message = "Price must be smaller than 50")
    private double price;

    @OneToMany(mappedBy = "pizza", orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Discount> discounts = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Ingredient> ingredients;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
