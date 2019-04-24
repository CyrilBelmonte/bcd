package com.ucp.cookwithease.dao;

import com.ucp.cookwithease.dao.general.CommentDAO;
import com.ucp.cookwithease.dao.general.RecipeDAO;
import com.ucp.cookwithease.dao.general.UserDAO;
import com.ucp.cookwithease.dao.mysql.CommentDAOMySQL;
import com.ucp.cookwithease.dao.mysql.RecipeDAOMySQL;
import com.ucp.cookwithease.dao.mysql.UserDAOMySQL;
import com.ucp.cookwithease.databases.MySQLConnection;

import java.sql.Connection;


public class DAOFactory {
    private static final Connection connection = MySQLConnection.getConnection();

    public static UserDAO getUserDAO() {
        return new UserDAOMySQL(connection);
    }

    public static RecipeDAO getRecipeDAO() {
        return new RecipeDAOMySQL(connection);
    }

    public static CommentDAO getCommentDAO() {
        return new CommentDAOMySQL(connection);
    }
}
