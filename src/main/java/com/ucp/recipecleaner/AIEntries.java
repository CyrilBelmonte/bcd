package com.ucp.recipecleaner;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Ingredient;
import com.ucp.cookwithease.model.Recipe;

import java.util.*;


public class AIEntries {
    private static RecipeCleaner cleaner = new RecipeCleaner();

    private AIEntries() {}

    private static LinkedList<String> getPartsOfRecipesName(LinkedList<Recipe> recipes) {
        int count;

        String recipeName;
        String globalName = "DEFAULT";

        LinkedList<String> nameParts;
        LinkedList<String> namePartsSorted = new LinkedList<>();
        LinkedList<String> results = new LinkedList<>();

        HashMap<String, Integer> namePartsIndex = new HashMap<>();

        for (Recipe recipe : recipes) {
            recipeName = recipe.getName();
            nameParts = cleaner.getCleanedRecipeName(recipeName);
            namePartsSorted.addAll(nameParts);
        }

        Collections.sort(namePartsSorted);

        for (String namePart : namePartsSorted) {
            if (!namePart.contains(globalName)) {
                globalName = namePart;
                count = 1;

            } else {
                count = namePartsIndex.get(globalName) + 1;
            }

            namePartsIndex.put(globalName, count);
        }

        for (Map.Entry<String, Integer> namesSet : namePartsIndex.entrySet()) {
            if (namesSet.getValue() > 1) {
                results.add(namesSet.getKey());
            }
        }

        return results;
    }

    private static LinkedList<String> deleteUnwantedWordsFromList(LinkedList<String> words) {
        LinkedList<String> excludedWords = new LinkedList<>(Arrays.asList(
            "sel", "poivre", "sucre", "sucre en poudre", "beurre", "farine", "eau", "lait",
            "huile", "huile d'olive"
        ));

        for (String excludedWord : excludedWords) {
            words.remove(excludedWord);
        }

        return words;
    }

    public static LinkedList<String> getPartsOfStartersName() {
        LinkedList<Recipe> starters = DAOFactory.getRecipeDAO().findAllStarters();

        return getPartsOfRecipesName(starters);
    }

    public static LinkedList<String> getPartsOfMainCoursesName() {
        LinkedList<Recipe> mainCourses = DAOFactory.getRecipeDAO().findAllMainCourses();

        return getPartsOfRecipesName(mainCourses);
    }

    public static LinkedList<String> getPartsOfDessertsName() {
        LinkedList<Recipe> desserts = DAOFactory.getRecipeDAO().findAllDesserts();

        return getPartsOfRecipesName(desserts);
    }

    public static LinkedList<String> getAllStartersIngredients() {
        LinkedList<String> ingredients = DAOFactory.getIngredientDAO().getAllStartersIngredients(3);

        return deleteUnwantedWordsFromList(ingredients);
    }

    public static LinkedList<String> getAllMainCoursesIngredients() {
        LinkedList<String> ingredients = DAOFactory.getIngredientDAO().getAllMainCoursesIngredients(3);

        return deleteUnwantedWordsFromList(ingredients);
    }

    public static LinkedList<String> getAllDessertsIngredients() {
        LinkedList<String> ingredients = DAOFactory.getIngredientDAO().getAllDessertsIngredients(3);

        return deleteUnwantedWordsFromList(ingredients);
    }

    public static HashMap<String, Double> getMaxIngredientsQuantities() {
        LinkedList<Ingredient> ingredients;
        LinkedList<Recipe> recipes = DAOFactory.getRecipeDAO().findAll();
        HashMap<String, Double> maxQuantities = new HashMap<>();

        String ingredientName;
        String unit;

        double quantity;
        double normalizedQuantity;
        double maxIngredientQuantity;

        for (Recipe recipe : recipes) {
            ingredients = recipe.getIngredients();

            for (Ingredient ingredient : ingredients) {
                ingredientName = ingredient.getCleanedName();

                if (maxQuantities.containsKey(ingredientName)) {
                    maxIngredientQuantity = maxQuantities.get(ingredientName);

                } else {
                    maxQuantities.put(ingredientName, 1.0);
                    maxIngredientQuantity = 1.0;
                }

                quantity = ingredient.getQuantity() / recipe.getPersons();
                unit = ingredient.getUnit();

                normalizedQuantity = AITools.normalizeQuantity(quantity, unit);

                if (normalizedQuantity > maxIngredientQuantity) {
                    maxQuantities.put(ingredientName, normalizedQuantity);
                }
            }
        }

        return maxQuantities;
    }
}
