package com.ucp.scraper_updated.engine;

import com.ucp.cookwithease.model.DishType;
import com.ucp.cookwithease.model.Ingredient;
import com.ucp.cookwithease.model.Level;
import com.ucp.recipecleaner.RecipeCleaner;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RecipeFormatter {
    private Pattern recipeDurationPattern;
    private Pattern ingredientPattern;
    private RecipeCleaner cleaner = new RecipeCleaner();

    public RecipeFormatter() {
        initRecipeDurationPattern();
        initIngredientPattern();
    }

    private void initRecipeDurationPattern() {
        String recipeDurationRegex = "(?:(?<hours>[0-9]+)h)?(?<minutes>[0-9]+)( min)?";

        recipeDurationPattern = Pattern.compile(recipeDurationRegex);
    }

    private void initIngredientPattern() {
        String ingredientRegex =
            ".*?(?:(?<adjective>#ADJECTIVES#)\\s)?\\s*" +
            "(?:(?<unit>#UNITS#)\\s)?(?:demi|moitié)?\\s*" +
            "(?:[a-z]{0,2} |[a-z]')?\\s*(?<name>.*)";

        LinkedList<String> allowedUnits = new LinkedList<>(Arrays.asList(
            "ml", "cl", "dl", "l", "mg", "g", "kg",
            "cuillère[s]?(?: à (?:soupe|café))?", "tasse[s]?(?: à café)?", "bol",
            "tranche", "branche", "feuille", "bouquet", "brin", "botte", "fleur",
            "gousse", "bûche", "bâton", "verre", "brique", "briquette", "part", "bloc",
            "barquette", "pot", "boîte", "sachet", "paquet", "rouleau", "graine",
            "poignée", "pincée", "zeste", "portion", "morceau", "filet", "cuisse",
            "pointe", "bouteille", "dose", "quartier", "goutte", "extrait", "flocon",
            "tablette", "plaque", "cube", "carré", "blanc", "jaune", "boule", "bocal",
            "brisure", "pavé", "pilon", "coeur", "épaule", "escalope", "aiguillette",
            "rondelle", "louche", "bûchette"
        ));

        LinkedList<String> excludedAdjectives = new LinkedList<>(Arrays.asList(
            "petit(?:e)?", "grand(?:e)?", "gros(?:se)?", "bon(?:ne)?", "fin(?:e)?",
            "beau", "belle"
        ));


        StringBuilder unitsRegex = new StringBuilder();
        String pipe = "";

        for (String unit : allowedUnits) {
            unitsRegex.append(pipe);
            unitsRegex.append(unit);
            unitsRegex.append("[sx]?");
            pipe = "|";
        }

        StringBuilder adjectivesRegex = new StringBuilder();
        pipe = "";

        for (String adjective : excludedAdjectives) {
            adjectivesRegex.append(pipe);
            adjectivesRegex.append(adjective);
            adjectivesRegex.append("[sx]?");
            pipe = "|";
        }

        ingredientRegex = ingredientRegex.replace("#ADJECTIVES#", adjectivesRegex)
                                         .replace("#UNITS#", unitsRegex);

        ingredientPattern = Pattern.compile(ingredientRegex);
    }

    public int getRecipeDurationFromString(String str) {
        int recipeDuration = 0;
        Matcher recipeDurationMatcher = recipeDurationPattern.matcher(str);
        boolean hasSucceeded = recipeDurationMatcher.matches();

        if (hasSucceeded) {
            String hours = recipeDurationMatcher.group("hours");
            String minutes = recipeDurationMatcher.group("minutes");

            if (hours != null) {
                recipeDuration = Integer.parseInt(hours) * 60;
            }

            recipeDuration += Integer.parseInt(minutes);
        }

        return recipeDuration;
    }

    public Ingredient getIngredientFromString(String str) {
        str = str.toLowerCase();

        Ingredient ingredient = null;
        Matcher ingredientMatcher = ingredientPattern.matcher(str);
        boolean hasSucceeded = ingredientMatcher.matches();

        if (hasSucceeded) {
            String adjective = ingredientMatcher.group("adjective");
            String name = ingredientMatcher.group("name");
            String cleanedName = cleaner.getCleanedIngredientName(name);
            String unit = ingredientMatcher.group("unit");

            ingredient = new Ingredient();

            ingredient.setName(name);
            ingredient.setCleanedName(cleanedName);
            ingredient.setUnit(unit);
        }

        return ingredient;
    }

    public DishType getDishTypeFromString(String str) {
        str = str.toLowerCase();

        if (str.contains("amuse-gueule") || str.contains("apéritif")) {
            return DishType.APPETIZER;

        } else if (str.contains("entrée")) {
            return DishType.STARTER;

        } else if (str.contains("plat")) {
            return DishType.MAIN_COURSE;

        } else if (str.contains("dessert")) {
            return DishType.DESSERT;

        } else {
            return DishType.OTHER;
        }
    }

    public Level getLevelFromString(String str) {
        str = str.toLowerCase();

        if (str.contains("moyen")) {
            return Level.AVERAGE;

        } else if (str.contains("difficile") || str.contains("cher")) {
            return Level.HIGH;

        } else {
            return Level.LOW;
        }
    }
}
