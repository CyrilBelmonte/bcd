package com.ucp.ai_experiments;

import com.ucp.cookwithease.model.Ingredient;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.recipecleaner.AITools;

import java.util.HashMap;
import java.util.LinkedList;


public class AIEntry extends AIObject {
    private Recipe recipe;

    @Override
    public void initializeCoordinates(LinkedList<String> ingredientsName, LinkedList<String> recipesName) {
        super.initializeCoordinates(ingredientsName, recipesName);

        HashMap<String, Double> maxIngredientsQuantities = Constants.getMaxIngredientsQuantities();

        String unit;
        double quantity;

        Ingredient ingredient;

        for (String ingredientName : ingredientsName) {
            if (recipe.getName().contains(ingredientName)) {
                setCoordinateValue(ingredientName, 1.0);
                continue;
            }

            if (recipe.hasIngredient(ingredientName)) {
                ingredient = recipe.getIngredientFromName(ingredientName);
                quantity = ingredient.getQuantity() / recipe.getPersons();
                unit = ingredient.getUnit();

                quantity = AITools.normalizeQuantity(quantity, unit);
                quantity /= maxIngredientsQuantities.get(ingredientName);

                setCoordinateValue(ingredientName, quantity);
            }
        }

        for (String recipeNamePart : recipesName) {
            if (AITools.contains(recipe.getName(), recipeNamePart)) {
                setCoordinateValue(recipeNamePart, 1.0);
            }
        }
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
