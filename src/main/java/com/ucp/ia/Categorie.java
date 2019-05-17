
package com.ucp.ia;

import com.ucp.cookwithease.model.*;

import java.util.LinkedList;

import static java.lang.Math.abs;


/**
 * categorie of cook Recipe
 */

public class Categorie {
    private LinkedList<Recipe> recipes;
    private LinkedList<Double> Distance;
    private int DistanceCat[];
     public Categorie(int sizemax,int position) {
        recipes=new LinkedList<>();
        Distance=new LinkedList<>();
        DistanceCat = new int[sizemax];
        for(int index=0 ; index < sizemax; index++){
            DistanceCat[index]= abs(index-position);
         }

    }
    public LinkedList<Recipe> getRecipes() {
        return recipes;
    }

    public int getDistanceCat(int indice) {
        return DistanceCat[indice];
    }

    public void setRecipes(LinkedList<Recipe> recipes) {
        this.recipes = recipes;
    }

    public LinkedList<Double> getDistance() {
        return Distance;
    }

    public void setDistance(LinkedList<Double> distance) {
        Distance = distance;
    }
}

