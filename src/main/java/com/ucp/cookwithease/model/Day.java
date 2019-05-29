package com.ucp.cookwithease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Day {
    private String name;
    private LinkedList<Recipe> lunchRecipes = new LinkedList<>();
    private LinkedList<Recipe> dinnerRecipes = new LinkedList<>();

    public void addLunchRecipe(Recipe recipe) {
        lunchRecipes.addLast(recipe);
    }

    public void addDinnerRecipe(Recipe recipe) {
        dinnerRecipes.addLast(recipe);
    }
}
