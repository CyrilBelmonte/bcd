package com.ucp.recipecleaner;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Recipe;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


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
        return DAOFactory.getIngredientDAO().getAllStartersIngredients();
    }

    public static LinkedList<String> getAllMainCoursesIngredients() {
        return DAOFactory.getIngredientDAO().getAllMainCoursesIngredients();
    }

    public static LinkedList<String> getAllDessertsIngredients() {
        return DAOFactory.getIngredientDAO().getAllDessertsIngredients();
    }
}
