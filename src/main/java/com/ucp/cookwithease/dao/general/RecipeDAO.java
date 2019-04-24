package com.ucp.cookwithease.dao.general;

import com.ucp.cookwithease.model.Recipe;

import java.sql.Connection;


public abstract class RecipeDAO {
    protected Connection connection;

    public RecipeDAO(Connection connection) {
        this.connection = connection;
    }

    // Select
    public abstract Recipe find(int recipeID);

    // Insert
    public abstract boolean insert(Recipe recipe);
}
