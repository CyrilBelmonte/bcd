package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.general.CommentDAO;
import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;

import java.sql.Connection;
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
        throw new UnsupportedOperationException();
    }
}
