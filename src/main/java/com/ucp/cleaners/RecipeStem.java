package com.ucp.cleaners;

import com.ucp.cookwithease.dao.DAOFactory;

import java.util.*;
import java.util.regex.Pattern;


public class RecipeStem {
    private Pattern ingredientStemPattern;

    public RecipeStem() {
        initIngredientStemPattern();
    }

    private void initIngredientStemPattern() {
        String regex = "([ea]u[x]?|l[l]?[e]?[s]?|[s]?)(\\b|$)";

        ingredientStemPattern = Pattern.compile(regex);
    }

    private String stemIngredient(String ingredient) {
        return ingredientStemPattern.matcher(ingredient).replaceAll("");
    }

    public HashMap<String, LinkedHashSet<String>> executeIngredientStemming() {
        LinkedList<String> ingredients = DAOFactory.getIngredientDAO().getAllIngredients();

        String base;
        String ingredientName;

        HashMap<String, LinkedHashSet<String>> ingredientStems = new HashMap<>();

        for (String ingredient : ingredients) {
            base = stemIngredient(ingredient);

            if (!ingredientStems.containsKey(base)) {
                ingredientStems.put(base, new LinkedHashSet<String>());
            }

            ingredientStems.get(base).add(ingredient);
        }

        LinkedHashSet<String> ingredientStem;
        LinkedList<String> ingredientBases = new LinkedList<>(ingredientStems.keySet());

        for (String ingredientBase : ingredientBases) {
            ingredientName = Collections.min(ingredientStems.get(ingredientBase));
            ingredientStem = ingredientStems.remove(ingredientBase);

            if (ingredientStem.size() > 1) {
                ingredientStems.put(ingredientName, ingredientStem);
            }
        }

        return ingredientStems;
    }
}
