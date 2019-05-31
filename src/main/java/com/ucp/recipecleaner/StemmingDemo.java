package com.ucp.recipecleaner;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;


public class StemmingDemo {
    public static void main(String[] args) {
        RecipeStem stem = new RecipeStem();
        HashMap<String, LinkedHashSet<String>> ingredientStems = stem.executeIngredientStemming();

        for (Map.Entry<String, LinkedHashSet<String>> ingredientStem : ingredientStems.entrySet()) {
            if (ingredientStem.getValue().size() > 1) {
                System.out.println("*** " + ingredientStem.getKey() + " ***");

                for (String ingredient : ingredientStem.getValue()) {
                    System.out.println("  " + ingredient);
                }

                System.out.println();
            }
        }

        String separator;
        String ingredientName;
        LinkedHashSet<String> ingredientAlternativeNames;
        StringBuilder queryResult;

        System.out.println("*** QUERIES ***");

        for (Map.Entry<String, LinkedHashSet<String>> ingredientStem : ingredientStems.entrySet()) {
            ingredientName = ingredientStem.getKey();
            ingredientAlternativeNames = ingredientStem.getValue();
            ingredientAlternativeNames.remove(ingredientName);

            separator = "";

            queryResult = new StringBuilder();

            queryResult.append("UPDATE ingredient SET cleanedName = '");
            queryResult.append(ingredientName);
            queryResult.append("' WHERE cleanedName IN (");

            for (String ingredientAlternativeName : ingredientAlternativeNames) {
                queryResult.append(separator);
                queryResult.append("'");
                queryResult.append(ingredientAlternativeName);
                queryResult.append("'");

                separator = ", ";
            }

            queryResult.append(");");

            System.out.println(queryResult);
        }
    }
}
