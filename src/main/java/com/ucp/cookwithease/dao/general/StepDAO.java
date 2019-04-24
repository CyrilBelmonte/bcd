package com.ucp.cookwithease.dao.general;

import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.Step;

import java.sql.Connection;
import java.util.LinkedList;


public abstract class StepDAO {
    protected Connection connection;

    public StepDAO(Connection connection) {
        this.connection = connection;
    }

    // Select
    public abstract LinkedList<Step> findAll(int recipeID);
    public abstract LinkedList<Step> findAll(Recipe recipe);

    // Insert
    public abstract boolean insert(Step step);
}
