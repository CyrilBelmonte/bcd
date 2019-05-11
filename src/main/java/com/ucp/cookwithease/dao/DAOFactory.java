package com.ucp.cookwithease.dao;

import com.ucp.cookwithease.dao.connections.LuceneConnection;
import com.ucp.cookwithease.dao.general.*;
import com.ucp.cookwithease.dao.lucene.LuceneIndex;
import com.ucp.cookwithease.dao.mysql.*;
import com.ucp.cookwithease.dao.connections.MySQLConnection;

import java.sql.Connection;


public class DAOFactory {
    private static final Connection connection = MySQLConnection.getConnection();
    private static final LuceneIndex luceneIndex = LuceneConnection.getConnection();

    public static UserDAO getUserDAO() {
        return new UserDAOMySQL(connection);
    }

    public static RecipeDAO getRecipeDAO() {
        return new RecipeDAOMySQL(connection);
    }

    public static IngredientDAO getIngredientDAO() {
        return new IngredientDAOMySQL(connection);
    }

    public static StepDAO getStepDAO() {
        return new StepDAOMySQL(connection);
    }

    public static CommentDAO getCommentDAO() {
        return new CommentDAOMySQL(connection);
    }

    public static Index getIndex() {
        return luceneIndex;
    }
}
