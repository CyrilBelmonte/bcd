package com.ucp.cookwithease.dao.general;

import com.ucp.cookwithease.model.Ingredient;
import com.ucp.cookwithease.model.Recipe;

import java.sql.Connection;
import java.util.LinkedList;


public abstract class IngredientDAO {
    protected Connection connection;

    public IngredientDAO(Connection connection) {
        this.connection = connection;
    }

    // Select
    public abstract LinkedList<Ingredient> findAll(int recipeID);
    public abstract LinkedList<Ingredient> findAll(Recipe recipe);

    // Insert
    public abstract boolean insert(Ingredient ingredient);
}
