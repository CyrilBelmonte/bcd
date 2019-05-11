package com.ucp.cookwithease.dao;

import com.ucp.cookwithease.dao.general.Index;
import com.ucp.cookwithease.model.Recipe;

import java.util.LinkedList;


public class IndexTest {
    public static void main(String[] args) {
        Index index = DAOFactory.getIndex();

        index.create();
        LinkedList<Recipe> recipesToIndex = DAOFactory.getRecipeDAO().findAll();
        index.addRecipes(recipesToIndex);
        index.close();

        LinkedList<Integer> recipesID = index.findRecipesId("chocolat", 10);
        LinkedList<Recipe> recipes = DAOFactory.getRecipeDAO().findAll(recipesID);

        for (Recipe recipe : recipes) {
            System.out.println(recipe);
        }
    }
}
