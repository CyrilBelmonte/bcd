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
    public LinkedList<Recipe> findAll() {
        return findAll(1000000000);
    }

    @Override
    public LinkedList<Recipe> findAll(int maxResults) {
        String query = "SELECT * FROM recipe LIMIT ?";
        Recipe recipe;
        LinkedList<Recipe> recipes = new LinkedList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, maxResults);

            ResultSet result = statement.executeQuery();

            while ((recipe = getRecipeFromRSet(result)) != null) {
                recipes.addLast(recipe);
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return recipes;
    }

    @Override
    public LinkedList<Recipe> findAll(LinkedList<Integer> recipesID) {
        String query = "SELECT * FROM recipe WHERE id IN (???) ORDER BY FIELD (id, ???)";

        Recipe recipe;
        LinkedList<Recipe> recipes = new LinkedList<>();

        int recipesLength = recipesID.size();

        if (recipesLength == 0) {
            return recipes;
        }

        StringBuffer stringBuffer = new StringBuffer();
        String separator = "";

        for (int id : recipesID) {
            stringBuffer.append(separator);
            stringBuffer.append("?");
            separator = ", ";
        }

        query = query.replace("???", stringBuffer);

        try {
            PreparedStatement statement = connection.prepareStatement(query);

            for (int i = 0; i < recipesLength; i++) {
                statement.setInt(i + 1, recipesID.get(i));
                statement.setInt(recipesLength + i + 1, recipesID.get(i));
            }

            ResultSet result = statement.executeQuery();

            while ((recipe = getRecipeFromRSet(result)) != null) {
                recipes.addLast(recipe);
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return recipes;
    }

    @Override
    public Recipe find(int id) {
        String query = "SELECT * FROM recipe WHERE id = ?";
        Recipe recipe = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            recipe = getRecipeFromRSet(result);
            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
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

                for (Ingredient ingredient : ingredients) {
                    hasSucceeded = hasSucceeded && ingredientDAO.insert(ingredient);
                }

                for (Step step : steps) {
                    hasSucceeded = hasSucceeded && stepDAO.insert(step);
                }
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return hasSucceeded;
    }

    @Override
    public boolean updateRating(int recipeID) {
        String query = "UPDATE recipe SET rating = (SELECT AVG(rating) FROM comment WHERE recipeID = ?) WHERE id = ?";

        boolean hasSucceeded = false;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, recipeID);
            statement.setInt(2, recipeID);
            int updatedTuples = statement.executeUpdate();

            if (updatedTuples > 0) {
                hasSucceeded = true;
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return hasSucceeded;
    }

    private Recipe getRecipeFromRSet(ResultSet resultSet) {
        Recipe recipe = null;

        try {
            if (resultSet.next()) {
                int recipeID = resultSet.getInt("id");

                recipe = new Recipe(
                    recipeID,
                    resultSet.getString("name"),
                    resultSet.getInt("duration"),
                    resultSet.getInt("persons"),
                    DishType.valueOf(resultSet.getString("type")),
                    Level.valueOf(resultSet.getString("cost")),
                    Level.valueOf(resultSet.getString("difficulty")),
                    resultSet.getFloat("rating"),
                    resultSet.getString("picture"),
                    DAOFactory.getIngredientDAO().findAll(recipeID),
                    DAOFactory.getStepDAO().findAll(recipeID),
                    DAOFactory.getCommentDAO().findAllByRecipe(recipeID));
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] getRecipeFromRSet : " + e.getMessage());
        }

        return recipe;
    }
}
