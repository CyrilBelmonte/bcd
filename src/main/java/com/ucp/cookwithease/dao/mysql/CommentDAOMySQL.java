package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.general.CommentDAO;
import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;

import java.sql.*;
import java.util.LinkedList;


public class CommentDAOMySQL extends CommentDAO {
    public CommentDAOMySQL(Connection connection) {
        super(connection);
    }

    @Override
    public LinkedList<Comment> findAll(int recipeID) {
        throw new UnsupportedOperationException();
    }

    @Override
    public LinkedList<Comment> findAll(Recipe recipe) {
        return findAll(recipe.getId());
    }

    @Override
    public LinkedList<Comment> findAll(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean insert(Comment comment) {
        String query =
            "INSERT INTO comment" +
            "(userID, recipeID, description, rating, publicationDate)" +
            "VALUES (?, ?, ?, ?, ?)";

        boolean hasSucceeded = false;

        try {
            java.sql.Date publicationDate = new java.sql.Date(comment.getPublicationDate().getTime());

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, comment.getUserID());
            statement.setInt(2, comment.getRecipeID());
            statement.setString(3, comment.getDescription());
            statement.setInt(4, comment.getRating());
            statement.setDate(5, publicationDate);

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
}
