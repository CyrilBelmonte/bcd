package Ia;

import com.ucp.scrapper.Data.Recipe;

import java.util.LinkedList;

/**
 * categorie of cook Recipe
 */
public class Categorie {
    public LinkedList<Recipe> recipes;

    public LinkedList<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(LinkedList<Recipe> recipes) {
        this.recipes = recipes;
    }
}
