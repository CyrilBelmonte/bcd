package com.ucp.scrapper.Data.RecipeData;

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

    private ArrayList<com.ucp.scrapper.data.recipedata.Ingredient> ingredients = new ArrayList<>();
    private ArrayList<com.ucp.scrapper.data.recipedata.Step> steps = new ArrayList<>();

    private String tags;

}
