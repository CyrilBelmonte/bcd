package com.ucp.scrapper.Data.RecipeData;


import com.ucp.scrapper.data.recipedata.Ingredient;
import com.ucp.scrapper.data.recipedata.Step;
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

}
