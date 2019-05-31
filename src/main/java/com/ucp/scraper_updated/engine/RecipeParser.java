package com.ucp.scraper_updated.engine;

import com.ucp.cleaners.RecipeFormatter;
import com.ucp.cookwithease.model.*;
import com.ucp.cookwithease.tools.Tools;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedList;


public class RecipeParser {
    private Document webDocument;
    private RecipeFormatter formatter;

    public RecipeParser() {
        formatter = new RecipeFormatter();
    }

    public Recipe parse(String url) {
        Scraper scraper = new Scraper(url);
        webDocument = scraper.getWebDocument();

        if (webDocument == null) {
            return null;
        }

        String name = getName();
        int duration = getDuration();
        int persons = getPersons();
        DishType type = getType();
        Level cost = getCost();
        Level difficulty = getDifficulty();
        float rating = getRating();
        String picture = getPicture();
        LinkedList<Ingredient> ingredients = getIngredients();
        LinkedList<Step> steps = getSteps();

        if (name == null || name.isEmpty() || picture == null || picture.isEmpty() ||
            duration == 0 || persons == 0 || rating == 0 ||
            type == null || cost == null || difficulty == null ||
            ingredients.size() == 0 || steps.size() == 0) {

            return null;
        }

        Recipe recipe = new Recipe();

        recipe.setName(Tools.capitalize(name));
        recipe.setDuration(duration);
        recipe.setPersons(persons);
        recipe.setType(type);
        recipe.setCost(cost);
        recipe.setDifficulty(difficulty);
        recipe.setRating(rating);
        recipe.setPicture(picture);
        recipe.setIngredients(ingredients);
        recipe.setSteps(steps);

        return recipe;
    }

    private String getName() {
        return getValueFromWeb(MarmitonReferences.RECIPE_NAME_PATH_SELECTOR);
    }

    private int getDuration() {
        String duration = getValueFromWeb(MarmitonReferences.DURATION_PATH_SELECTOR);

        if (duration != null) {
            return formatter.getRecipeDurationFromString(duration);

        } else {
            return 0;
        }
    }

    private int getPersons() {
        return getIntValueFromWeb(MarmitonReferences.NUM_PERSONS_PATH_SELECTOR);
    }

    private DishType getType() {
        StringBuilder tags = new StringBuilder();
        Elements elements = getValuesFromWeb(MarmitonReferences.RECIPE_TAGS_PATH_SELECTOR);

        if (elements == null) {
            return null;
        }

        for (Element element : elements) {
            tags.append(element.getElementsByClass("mrtn-tag").get(0).ownText());
            tags.append(" ");
        }

        return formatter.getDishTypeFromString(tags.toString());
    }

    private Level getCost() {
        return getLevelValueFromWeb(MarmitonReferences.COST_PATH_SELECTOR);
    }

    private Level getDifficulty() {
        return getLevelValueFromWeb(MarmitonReferences.DIFFICULTY_PATH_SELECTOR);
    }

    private Float getRating() {
        return getFloatValueFromWeb(MarmitonReferences.RATING_PATH_SELECTOR);
    }

    private String getPicture() {
        return getAttributeValueFromWeb(MarmitonReferences.PICTURE_PATH_SELECTOR, "src");
    }

    private LinkedList<Ingredient> getIngredients() {
        Ingredient ingredient;
        LinkedList<Ingredient> ingredients = new LinkedList<>();
        Elements elements = getValuesFromWeb(MarmitonReferences.INGREDIENTS_PATH_SELECTOR);

        if (elements == null) {
            return ingredients;
        }

        String description;
        String quantityStr;
        float quantity;

        for (Element element : elements) {
            description = element.getElementsByClass("name_singular").attr("data-name-singular");
            ingredient = formatter.getIngredientFromString(description);

            quantityStr = element.getElementsByClass("recipe-ingredient-qt").get(0).attr("data-base-qt");
            quantity = !quantityStr.isEmpty() ? Float.parseFloat(quantityStr) : 0.0f;
            ingredient.setQuantity(quantity);

            ingredients.add(ingredient);
        }

        return ingredients;
    }

    private LinkedList<Step> getSteps() {
        Step step;
        LinkedList<Step> steps = new LinkedList<>();
        Elements elements = getValuesFromWeb(MarmitonReferences.STEPS_PATH_SELECTOR);

        if (elements == null) {
            return steps;
        }

        String name;
        String description;
        int position;

        for (Element element : elements) {
            step = new Step();
            name = element.select("h3.__secondary").get(0).ownText();
            position = Integer.parseInt(name.split(" ")[1]);
            description = element.getElementsByClass("recipe-preparation__list__item").get(0).ownText();

            step.setPosition(position);
            step.setDescription(description);

            steps.add(step);
        }

        return steps;
    }

    private int getIntValueFromWeb(String selector) {
        String value = getValueFromWeb(selector);

        if (value != null && !value.isEmpty()) {
            return Integer.parseInt(value);

        } else {
            return 0;
        }
    }

    private float getFloatValueFromWeb(String selector) {
        String value = getValueFromWeb(selector);

        if (value != null && !value.isEmpty()) {
            return Float.parseFloat(value);

        } else {
            return 0.0f;
        }
    }

    private Level getLevelValueFromWeb(String selector) {
        String value = getValueFromWeb(selector);

        if (value != null) {
            return formatter.getLevelFromString(value);

        } else {
            return null;
        }
    }

    private String getValueFromWeb(String selector) {
        if (!webDocument.select(selector).isEmpty()) {
            return webDocument.select(selector).get(0).ownText();

        } else {
            return null;
        }
    }

    private Elements getValuesFromWeb(String selector) {
        if (!webDocument.select(selector).isEmpty()) {
            return webDocument.select(selector);

        } else {
            return null;
        }
    }

    private String getAttributeValueFromWeb(String selector, String attribute) {
        if (!webDocument.select(selector).isEmpty()) {
            return webDocument.select(selector).attr(attribute);

        } else {
            return null;
        }
    }
}
