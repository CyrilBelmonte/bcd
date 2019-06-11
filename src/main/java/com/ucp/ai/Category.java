package com.ucp.ai;

import com.ucp.cookwithease.model.Recipe;

import java.util.LinkedList;

import static java.lang.Math.abs;


public class Category {
    private LinkedList<Recipe> recipes;
    private LinkedList<Double> distance;
    private int distanceCat[];

    public Category(int maxSize, int position) {
        recipes = new LinkedList<>();
        distance = new LinkedList<>();
        distanceCat = new int[maxSize];

        for (int index = 0; index < maxSize; index++) {
            distanceCat[index] = abs(index - position);
        }
    }

    public LinkedList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(LinkedList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public int getDistanceCat(int index) {
        return distanceCat[index];
    }

    public LinkedList<Double> getDistance() {
        return distance;
    }

    public void setDistance(LinkedList<Double> distance) {
        this.distance = distance;
    }
}

