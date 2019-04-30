package com.ucp.scrapper.data.recipedata;

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

    private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
    private ArrayList<Step> steps = new ArrayList<Step>();

    private String tags;

}
