package com.ucp.cookwithease.dao.general;

import com.ucp.cookwithease.model.Recipe;

import java.sql.Connection;
import java.util.LinkedList;


public abstract class RecipeDAO {
    protected Connection connection;

    public RecipeDAO(Connection connection) {
        this.connection = connection;
    }

    // Select
    public abstract LinkedList<Recipe> findAll();
    public abstract Recipe find(int id);

    // Insert
    public abstract boolean insert(Recipe recipe);
}
