package com.brassbeluga.bakingapp.model;

import lombok.Data;

@Data
public class Recipe {
    private int id;
    private String name;
    private int servings;
    private String image;
    private Ingredient[] ingredients;
    private Step[] steps;
}
