package com.ucp.scraper_updated.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Recipe;

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

    public void execute(int maxRecipes) {
        Recipe recipe;
        RecipeParser recipeParser = new RecipeParser();

        int i = 0;
        boolean hasSucceeded;

        System.out.println("*** Retrieving of " + maxRecipes + " recipes ***");

        while (i < maxRecipes) {
            recipe = recipeParser.parse("https://marmiton.org/recettes/recette-hasard.aspx");

            if (recipe == null) {
                continue;
            }

            if (hasRecipeInRegistry(recipe)) {
                System.err.println("[SKIPPED] Recipe #" + (i + 1) +
                                   " has already been processed (" + recipe.getName() + ")");
                continue;
            }

            hasSucceeded = DAOFactory.getRecipeDAO().insert(recipe);

            if (hasSucceeded) {
                System.out.println("[SUCCEEDED] Recipe #" + (i + 1) +
                                   " has been inserted (" + recipe.getName() + ")");
                addRecipeToRegistry(recipe);
                i++;

            } else {
                System.err.println("[FAILED] Recipe #" + (i + 1) +
                                   " has not been inserted (" + recipe.getName() + ")");
            }
        }

        System.out.println("***");
    }
}
