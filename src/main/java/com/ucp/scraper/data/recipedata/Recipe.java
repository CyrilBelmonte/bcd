package com.ucp.scraper.data.recipedata;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private int recipeTime;
    private int cookingTime;
    private int preparationTime;
    private int numberPersons;
    private float mark;

    private String economicLevel;
    private String difficultyLevel;
    private String title;
    private String picture;

    private ArrayList<Ingredient> ingredients = new ArrayList<>();
    private ArrayList<Step> steps = new ArrayList<>();

    private String tags;
}
