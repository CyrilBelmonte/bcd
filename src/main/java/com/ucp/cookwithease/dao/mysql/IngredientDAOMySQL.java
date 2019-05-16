package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.general.IngredientDAO;
import com.ucp.cookwithease.model.DishType;
import com.ucp.cookwithease.model.Ingredient;
import com.ucp.cookwithease.model.Recipe;

import java.sql.*;
import java.util.LinkedList;


public class IngredientDAOMySQL extends IngredientDAO {
    public IngredientDAOMySQL(Connection connection) {
        super(connection);
    }

    @Override
    public LinkedList<Ingredient> findAll(int recipeID) {
        String query = "SELECT * FROM ingredient WHERE recipeID = ?";

        Ingredient ingredient;
        LinkedList<Ingredient> ingredients = new LinkedList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, recipeID);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ingredient = new Ingredient(
                    result.getInt("id"),
                    recipeID,
                    result.getString("name"),
                    result.getFloat("quantity"),
                    result.getString("unit"));

                ingredients.addLast(ingredient);
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return ingredients;
    }

    @Override
    public LinkedList<Ingredient> findAll(Recipe recipe) {
        return findAll(recipe.getId());
    }

    @Override
    public LinkedList<String> getAllIngredients() {
        String query = "SELECT DISTINCT name FROM ingredient";
        LinkedList<String> ingredients = new LinkedList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ingredients.addLast(result.getString("name"));
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return ingredients;
    }

    @Override
    public LinkedList<String> getAllIngredients(DishType type, int minOccurrences) {
        String query =
            "SELECT i.name, COUNT(*) as occurrences " +
            "FROM ingredient i, recipe r " +
            "WHERE i.recipeID = r.id AND type = ? " +
            "GROUP BY i.name " +
            "HAVING occurrences >= ?";

        LinkedList<String> ingredients = new LinkedList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, type.toString());
            statement.setInt(2, minOccurrences);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                ingredients.addLast(result.getString("name"));
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return ingredients;
    }

    @Override
    public LinkedList<String> getAllStartersIngredients() {
        return getAllStartersIngredients(1);
    }

    @Override
    public LinkedList<String> getAllMainCoursesIngredients() {
        return getAllMainCoursesIngredients(1);
    }

    @Override
    public LinkedList<String> getAllDessertsIngredients() {
        return getAllDessertsIngredients(1);
    }

    @Override
    public LinkedList<String> getAllStartersIngredients(int minOccurrences) {
        return getAllIngredients(DishType.STARTER, minOccurrences);
    }

    @Override
    public LinkedList<String> getAllMainCoursesIngredients(int minOccurrences) {
        return getAllIngredients(DishType.MAIN_COURSE, minOccurrences);
    }

    @Override
    public LinkedList<String> getAllDessertsIngredients(int minOccurrences) {
        return getAllIngredients(DishType.DESSERT, minOccurrences);
    }

    @Override
    public boolean insert(Ingredient ingredient) {
        String query =
            "INSERT INTO ingredient" +
            "(name, quantity, unit, recipeID)" +
            "VALUES (?, ?, ?, ?)";

        boolean hasSucceeded = false;

        try {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, ingredient.getName());
            statement.setFloat(2, ingredient.getQuantity());
            statement.setString(3, ingredient.getUnit());
            statement.setInt(4, ingredient.getRecipeID());
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();

            if (result.next()) {
                ingredient.setId(result.getInt(1));
                hasSucceeded = true;
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return hasSucceeded;
    }
}
