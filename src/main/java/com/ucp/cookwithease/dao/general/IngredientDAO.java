package com.ucp.cookwithease.dao.general;

import com.ucp.cookwithease.model.DishType;
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

    public abstract LinkedList<String> getAllIngredients();
    public abstract LinkedList<String> getAllIngredients(DishType type);
    public abstract LinkedList<String> getAllStartersIngredients();
    public abstract LinkedList<String> getAllMainCoursesIngredients();
    public abstract LinkedList<String> getAllDessertsIngredients();

    // Insert
    public abstract boolean insert(Ingredient ingredient);
}
