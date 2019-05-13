package com.ucp.cookwithease.dao.general;

import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;

import java.sql.Connection;
import java.util.LinkedList;


public abstract class CommentDAO {
    protected Connection connection;

    public CommentDAO(Connection connection) {
        this.connection = connection;
    }

    // Select
    public abstract LinkedList<Comment> findAllByRecipe(int recipeID);
    public abstract LinkedList<Comment> findAllByRecipe(Recipe recipe);
    public abstract LinkedList<Comment> findAllByUser(int userID);
    public abstract LinkedList<Comment> findAllByUser(User user);
    public abstract Comment find(int userID, int recipeID);

    // Insert
    public abstract boolean insert(Comment comment);
}
