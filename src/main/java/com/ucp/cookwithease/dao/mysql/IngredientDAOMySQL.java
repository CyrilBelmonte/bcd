package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.general.IngredientDAO;
import com.ucp.cookwithease.model.Ingredient;
import com.ucp.cookwithease.model.Recipe;

import java.sql.Connection;
import java.util.LinkedList;


public class IngredientDAOMySQL extends IngredientDAO {
    public IngredientDAOMySQL(Connection connection) {
        super(connection);
    }

    @Override
    public LinkedList<Ingredient> findAll(int recipeID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LinkedList<Ingredient> findAll(Recipe recipe) {
        return findAll(recipe.getId());
    }

    @Override
    public boolean insert(Ingredient ingredient) {
        throw new UnsupportedOperationException();
    }
}
