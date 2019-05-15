package com.ucp.recipecleaner;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Recipe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class MainTest {
    public static void main(String[] args) {
        RecipeCleaner recipeCleaner = new RecipeCleaner();

        int count;
        String[] standardizedName;

        LinkedList<Recipe> recipes = DAOFactory.getRecipeDAO().findAll();
        HashMap<String, Integer> recipesName = new HashMap<>();

        for (Recipe recipe : recipes) {
            standardizedName = recipeCleaner.getStandardizedRecipeName(recipe.getName());

            for (String word : standardizedName) {
                if (recipesName.containsKey(word)) {
                    count = recipesName.get(word) + 1;

                } else {
                    count = 1;
                }

                recipesName.put(word, count);
            }
        }

        for (Map.Entry<String, Integer> entrySet : recipesName.entrySet()) {
            if (entrySet.getValue() > 1) {
                System.out.println(entrySet.getKey() + " : " + entrySet.getValue());

            } else {
                System.err.println(entrySet.getKey() + " : " + entrySet.getValue());
            }

            System.out.flush();
            System.err.flush();
        }
    }
}
