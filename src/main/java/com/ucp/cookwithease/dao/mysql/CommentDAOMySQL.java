package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.dao.general.CommentDAO;
import com.ucp.cookwithease.model.*;

import java.sql.*;
import java.util.LinkedList;


public class CommentDAOMySQL extends CommentDAO {
    public CommentDAOMySQL(Connection connection) {
        super(connection);
    }

    @Override
    public LinkedList<Comment> findAllFromRecipe(int recipeID) {
        return findAll("SELECT * FROM comment WHERE recipeID = ? ORDER BY publicationDate DESC", recipeID);
    }

    @Override
    public LinkedList<Comment> findAllFromRecipe(Recipe recipe) {
        return findAllFromRecipe(recipe.getId());
    }

    @Override
    public LinkedList<Comment> findAllFromUser(int userID) {
        return findAll("SELECT * FROM comment WHERE userID = ? ORDER BY publicationDate DESC", userID);
    }

    @Override
    public LinkedList<Comment> findAllFromUser(User user) {
        return findAllFromUser(user.getId());
    }

    @Override
    public Comment find(int userID, int recipeID) {
        String query = "SELECT * FROM comment WHERE userID = ? AND recipeID = ?";
        Comment comment = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            statement.setInt(2, recipeID);

            ResultSet result = statement.executeQuery();
            comment = getCommentFromRSet(result);
            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return comment;
    }

    @Override
    public boolean insert(Comment comment) {
        String query =
            "INSERT INTO comment" +
            "(userID, recipeID, description, rating, publicationDate)" +
            "VALUES (?, ?, ?, ?, ?)";

        boolean hasSucceeded = false;

        try {
            java.sql.Date publicationDate = new java.sql.Date(
                comment.getPublicationDate().getTime());

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, comment.getUserID());
            statement.setInt(2, comment.getRecipeID());
            statement.setString(3, comment.getDescription());
            statement.setInt(4, comment.getRating());
            statement.setDate(5, publicationDate);
            int updatedTuples = statement.executeUpdate();

            if (updatedTuples > 0) {
                DAOFactory.getRecipeDAO().updateRating(comment.getRecipeID());
                hasSucceeded = true;
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return hasSucceeded;
    }

    private LinkedList<Comment> findAll(String query, int id) {
        Comment comment;
        LinkedList<Comment> comments = new LinkedList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            while ((comment = getCommentFromRSet(result)) != null) {
                comments.addLast(comment);
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());
        }

        return comments;
    }

    private Comment getCommentFromRSet(ResultSet resultSet) {
        Comment comment = null;

        try {
            if (resultSet.next()) {
                java.util.Date publicationDate = new java.util.Date(
                    resultSet.getDate("publicationDate").getTime());

                comment = new Comment(
                    resultSet.getInt("userID"),
                    resultSet.getInt("recipeID"),
                    "Unknown", "Unknown",
                    resultSet.getString("description"),
                    resultSet.getInt("rating"),
                    publicationDate);
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] getCommentFromRSet : " + e.getMessage());
        }

        return comment;
    }
}
