package com.ucp.ai_experiments;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public abstract class AIObject {
    protected HashMap<String, Double> ingredientsCoordinates;
    protected HashMap<String, Double> recipesNameCoordinates;

    public AIObject() {
        ingredientsCoordinates = new HashMap<>();
        recipesNameCoordinates = new HashMap<>();
    }

    public void initializeCoordinates(LinkedList<String> ingredientsName, LinkedList<String> recipesName) {
        for (String ingredientName : ingredientsName) {
            ingredientsCoordinates.put(ingredientName, 0.0);
        }

        for (String recipeName : recipesName) {
            recipesNameCoordinates.put(recipeName, 0.0);
        }
    }

    public double distanceTo(AIObject object) {
        String coordinateName;
        double currentCoordinate;
        double distantCoordinate;
        double distance = 0.0;

        for (Map.Entry<String, Double> ingredientME : ingredientsCoordinates.entrySet()) {
            coordinateName = ingredientME.getKey();
            currentCoordinate = ingredientME.getValue();
            distantCoordinate = object.getCoordinateValue(coordinateName);

            distance += Math.pow(currentCoordinate - distantCoordinate, 2);
        }

        for (Map.Entry<String, Double> recipeNameME : recipesNameCoordinates.entrySet()) {
            coordinateName = recipeNameME.getKey();
            currentCoordinate = recipeNameME.getValue();
            distantCoordinate = object.getCoordinateValue(coordinateName);

            distance += Math.pow(currentCoordinate - distantCoordinate, 2);
        }

        distance = Math.sqrt(distance);

        return distance;
    }

    public boolean hasCoordinateName(String coordinateName) {
        return ingredientsCoordinates.containsKey(coordinateName) ||
               recipesNameCoordinates.containsKey(coordinateName);
    }

    public double getCoordinateValue(String coordinateName) {
        if (ingredientsCoordinates.containsKey(coordinateName)) {
            return ingredientsCoordinates.get(coordinateName);
        }

        if (recipesNameCoordinates.containsKey(coordinateName)) {
            return recipesNameCoordinates.get(coordinateName);
        }

        return 0.0;
    }

    public void setCoordinateValue(String coordinateName, double coordinate) {
        if (ingredientsCoordinates.containsKey(coordinateName)) {
            ingredientsCoordinates.put(coordinateName, coordinate);

        } else if (recipesNameCoordinates.containsKey(coordinateName)) {
            recipesNameCoordinates.put(coordinateName, coordinate);
        }
    }

    public HashMap<String, Double> getIngredientsCoordinates() {
        return ingredientsCoordinates;
    }

    public HashMap<String, Double> getRecipesNameCoordinates() {
        return recipesNameCoordinates;
    }

    public void copyCoordinatesFrom(AIObject object) {
        ingredientsCoordinates = new HashMap<>(object.getIngredientsCoordinates());
        recipesNameCoordinates = new HashMap<>(object.getRecipesNameCoordinates());
    }
}
