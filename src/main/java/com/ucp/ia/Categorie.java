
package com.ucp.ia;

import com.ucp.cookwithease.model.*;

import java.util.LinkedList;


/**
 * categorie of cook Recipe
 */

public class Categorie {
    public LinkedList<Recipe> recipes;
     public Categorie() {
        recipes=new LinkedList<>();
    }
    public LinkedList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(LinkedList<Recipe> recipes) {
        this.recipes = recipes;
    }
}

