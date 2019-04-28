package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.dao.general.IngredientDAO;
import com.ucp.cookwithease.dao.general.RecipeDAO;
import com.ucp.cookwithease.dao.general.StepDAO;
import com.ucp.cookwithease.model.*;

import java.sql.*;
import java.util.LinkedList;


public class RecipeDAOMySQL extends RecipeDAO {
    public RecipeDAOMySQL(Connection connection) {
        super(connection);
    }

    @Override
    public Recipe find(int id) {
        String query = "SELECT * FROM recipe WHERE id = ?";

        Recipe recipe = null;
        LinkedList<Ingredient> ingredients;
        LinkedList<Step> steps;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            recipe = getRecipeFromRSet(result);
            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());

        }

        if (recipe != null) {
            ingredients = DAOFactory.getIngredientDAO().findAll(recipe);
            steps = DAOFactory.getStepDAO().findAll(recipe);

            recipe.setIngredients(ingredients);
            recipe.setSteps(steps);
        }

        return recipe;
    }

    @Override
    public boolean insert(Recipe recipe) {
        String query =
            "INSERT INTO recipe" +
            "(name, duration, persons, type, cost, difficulty, rating, picture)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        IngredientDAO ingredientDAO = DAOFactory.getIngredientDAO();
        StepDAO stepDAO = DAOFactory.getStepDAO();

        LinkedList<Ingredient> ingredients = recipe.getIngredients();
        LinkedList<Step> steps = recipe.getSteps();

        boolean hasSucceeded = false;

        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, recipe.getName());
            statement.setInt(2, recipe.getDuration());
            statement.setInt(3, recipe.getPersons());
            statement.setString(4, recipe.getType().toString());
            statement.setString(5, recipe.getCost().toString());
            statement.setString(6, recipe.getDifficulty().toString());
            statement.setFloat(7, recipe.getRating());
            statement.setString(8, recipe.getPicture());
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();

            if (result.next()) {
                recipe.setId(result.getInt(1));
                hasSucceeded = true;
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());

        }

        for (Ingredient ingredient : ingredients) {
            hasSucceeded = hasSucceeded && ingredientDAO.insert(ingredient);
        }

        for (Step step : steps) {
            hasSucceeded = hasSucceeded && stepDAO.insert(step);
        }

        return hasSucceeded;
    }

    private Recipe getRecipeFromRSet(ResultSet resultSet) {
        Recipe recipe = null;

        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int duration = resultSet.getInt("duration");
                int persons = resultSet.getInt("persons");
                DishType type = DishType.valueOf(resultSet.getString("type"));
                Level cost = Level.valueOf(resultSet.getString("cost"));
                Level difficulty = Level.valueOf(resultSet.getString("difficulty"));
                float rating = resultSet.getFloat("rating");
                String picture = resultSet.getString("picture");

                recipe = new Recipe();
                recipe.setId(id);
                recipe.setName(name);
                recipe.setDuration(duration);
                recipe.setPersons(persons);
                recipe.setType(type);
                recipe.setCost(cost);
                recipe.setDifficulty(difficulty);
                recipe.setRating(rating);
                recipe.setPicture(picture);
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] getRecipeFromRSet : " + e.getMessage());

        }

        return recipe;
    }
}
