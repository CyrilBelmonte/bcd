package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.general.RecipeDAO;
import com.ucp.cookwithease.model.Recipe;

import java.sql.Connection;


public class RecipeDAOMySQL extends RecipeDAO {
    public RecipeDAOMySQL(Connection connection) {
        super(connection);
    }

    @Override
    public Recipe find(int recipeID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean insert(Recipe recipe) {
        throw new UnsupportedOperationException();
    }
}
