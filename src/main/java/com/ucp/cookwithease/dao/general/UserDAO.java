package com.ucp.cookwithease.dao.general;

import com.ucp.cookwithease.model.User;

import java.sql.Connection;
import java.util.LinkedList;


public abstract class UserDAO {
    protected Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Select
    public abstract LinkedList<User> findAll();
    public abstract User find(int id);
    public abstract User find(String pseudo);
    public abstract User find(String pseudo, String password);

    // Insert
    public abstract boolean insert(User user);

    // Update
    public abstract boolean update(User user);
}
