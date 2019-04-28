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
        String query = "SELECT * FROM step WHERE recipeID = ?";

        int id;
        int position;
        String description;

        Step step;
        LinkedList<Step> steps = new LinkedList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, recipeID);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                id = result.getInt("id");
                position = result.getInt("position");
                description = result.getString("description");

                step = new Step();
                step.setId(id);
                step.setPosition(position);
                step.setDescription(description);
                step.setRecipeID(recipeID);

                steps.addLast(step);
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());

        }

        return steps;
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
