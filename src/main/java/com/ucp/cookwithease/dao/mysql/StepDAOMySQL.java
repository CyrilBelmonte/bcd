package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.general.StepDAO;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.Step;

import java.sql.*;
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
        String query =
            "INSERT INTO step" +
            "(position, description, recipeID)" +
            "VALUES (?, ?, ?)";

        boolean hasSucceeded = false;

        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, step.getPosition());
            statement.setString(2, step.getDescription());
            statement.setInt(3, step.getRecipeID());
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();

            if (result.next()) {
                step.setId(result.getInt(1));
                hasSucceeded = true;
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());

        }

        return hasSucceeded;
    }
}
