package com.ucp.ai_experiments;

import java.util.LinkedList;
import java.util.Map;


public class AINeuron extends AIObject {
    private int id;

    public void randomizeCoordinates() {
        String coordinateName;

        for (Map.Entry<String, Double> ingredientME : ingredientsCoordinates.entrySet()) {
            coordinateName = ingredientME.getKey();

            ingredientsCoordinates.put(coordinateName, Math.random());
        }

        for (Map.Entry<String, Double> recipeNameME : recipesNameCoordinates.entrySet()) {
            coordinateName = recipeNameME.getKey();

            recipesNameCoordinates.put(coordinateName, Math.random());
        }
    }

    @Override
    public void initializeCoordinates(LinkedList<String> ingredientsName, LinkedList<String> recipesName) {
        super.initializeCoordinates(ingredientsName, recipesName);
        randomizeCoordinates();
    }

    public double calculateActivity(AIEntry entry) {
        double potential = distanceTo(entry);
        double activity = 1 / (1 + potential);

        return activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
