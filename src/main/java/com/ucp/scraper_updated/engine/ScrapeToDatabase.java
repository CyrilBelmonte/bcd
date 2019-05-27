package com.ucp.scraper_updated.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.DishType;
import com.ucp.cookwithease.model.Recipe;

import java.util.ArrayList;
import java.util.HashMap;


public class ScrapeToDatabase {

    private HashMap<String, Recipe> recipesRegistry = new HashMap<>();

    private void addRecipeToRegistry(Recipe recipe) {
        recipesRegistry.put(recipe.getName(), recipe);
    }

    private Recipe getRecipeFromRegistry(String name) {
        return recipesRegistry.get(name);
    }

    private boolean hasRecipeInRegistry(String name) {
        return recipesRegistry.containsKey(name);
    }

    private boolean hasRecipeInRegistry(Recipe recipe) {
        return hasRecipeInRegistry(recipe.getName());
    }

    public void execute(int maxRecipes,ArrayList<String> urlRecipe) {


        Recipe recipe;
        RecipeParser recipeParser = new RecipeParser();

        int i = 0;
        boolean hasSucceeded;

        System.out.println("*** Retrieving of " + maxRecipes + " recipes ***");

        while (recipesRegistry.size() < maxRecipes) {
            recipe = recipeParser.parse(urlRecipe.get(i));

            if (recipe != null && recipe.getType() != DishType.OTHER) {
                hasSucceeded = DAOFactory.getRecipeDAO().insert(recipe);

                if (hasSucceeded) {
                    addRecipeToRegistry(recipe);

                    System.out.println("[SUCCEEDED] Recipe #" + recipesRegistry.size() +
                        " has been inserted | " + recipe.getName() +
                        " | URL: "+ urlRecipe.get(i));

                } else {
                    System.err.println("[FAILED] Recipe #" + recipesRegistry.size() +
                        " has not been inserted | " + recipe.getName() +
                        " | URL: "+ urlRecipe.get(i));
                }
            }

            i++;
        }

        System.out.println("***");
    }
}
