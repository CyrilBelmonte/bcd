package com.ucp.cookwithease.dao.general;

import com.ucp.cookwithease.model.Recipe;

import java.util.LinkedList;


public interface Index {
    // Indexer
    public abstract boolean open();
    public abstract boolean create();
    public abstract boolean close();

    public abstract boolean addRecipe(Recipe recipe);
    public abstract boolean addRecipes(LinkedList<Recipe> recipes);

    // Searcher
    public abstract LinkedList<Integer> findRecipesId(String keywords, int maxResults);
}
