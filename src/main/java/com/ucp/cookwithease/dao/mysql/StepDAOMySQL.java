package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.general.StepDAO;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.Step;

import java.sql.Connection;
import java.util.LinkedList;


public class StepDAOMySQL extends StepDAO {
    public StepDAOMySQL(Connection connection) {
        super(connection);
    }

    @Override
    public LinkedList<Step> findAll(int recipeID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LinkedList<Step> findAll(Recipe recipe) {
        return findAll(recipe.getId());
    }

    @Override
    public boolean insert(Step step) {
        throw new UnsupportedOperationException();
    }
}
