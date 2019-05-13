package com.ucp.cookwithease.dao.general;

import com.ucp.cookwithease.model.DishType;
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
    public abstract LinkedList<Recipe> findAll(int maxResults);

    public abstract LinkedList<Recipe> findAll(DishType type);
    public abstract LinkedList<Recipe> findAllStarters();
    public abstract LinkedList<Recipe> findAllMainCourses();
    public abstract LinkedList<Recipe> findAllDesserts();

    public abstract LinkedList<Recipe> findAll(LinkedList<Integer> recipesID);
    public abstract Recipe find(int id);

    // Insert
    public abstract boolean insert(Recipe recipe);

    // Update
    public abstract boolean updateRating(int recipeID);
}
