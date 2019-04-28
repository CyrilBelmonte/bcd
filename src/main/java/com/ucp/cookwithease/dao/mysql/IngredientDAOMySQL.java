package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.general.IngredientDAO;
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

        int id;
        String name;
        float quantity;
        String unit;

        Ingredient ingredient;
        LinkedList<Ingredient> ingredients = new LinkedList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, recipeID);

            ResultSet result = statement.executeQuery();

            while (result.next()) {
                id = result.getInt("id");
                name = result.getString("name");
                quantity = result.getInt("quantity");
                unit = result.getString("unit");

                ingredient = new Ingredient();
                ingredient.setId(id);
                ingredient.setName(name);
                ingredient.setQuantity(quantity);
                ingredient.setUnit(unit);
                ingredient.setRecipeID(recipeID);

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
