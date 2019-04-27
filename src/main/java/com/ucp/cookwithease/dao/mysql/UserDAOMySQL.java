package com.ucp.cookwithease.dao.mysql;

import com.ucp.cookwithease.dao.general.UserDAO;
import com.ucp.cookwithease.model.User;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;


public class UserDAOMySQL extends UserDAO {
    public UserDAOMySQL(Connection connection) {
        super(connection);
    }

    @Override
    public LinkedList<User> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public User find(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);

            ResultSet result = statement.executeQuery();
            user = getUserFromRSet(result);
            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());

        }

        return user;
    }

    @Override
    public User find(String pseudo) {
        String query = "SELECT * FROM user WHERE pseudo = ?";
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pseudo);

            ResultSet result = statement.executeQuery();
            user = getUserFromRSet(result);
            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());

        }

        return user;
    }

    @Override
    public User find(String pseudo, String password) {
        String query = "SELECT * FROM user WHERE pseudo = ? AND password = ?";
        User user = null;

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pseudo);
            statement.setString(2, password);

            ResultSet result = statement.executeQuery();
            user = getUserFromRSet(result);
            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());

        }

        return user;
    }

    @Override
    public boolean insert(User user) {
        String query =
            "INSERT INTO user" +
            "(firstName, lastName, pseudo, email, password, inscriptionDate)" +
            "VALUES (?, ?, ?, ?, ?, ?)";

        boolean hasSucceeded = false;

        try {
            java.sql.Date inscriptionDate = new java.sql.Date(user.getInscriptionDate().getTime());

            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getPseudo());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getPassword());
            statement.setDate(6, inscriptionDate);
            statement.executeUpdate();

            ResultSet result = statement.getGeneratedKeys();

            if (result.next()) {
                user.setId(result.getInt(1));
                hasSucceeded = true;
            }

            statement.close();

        } catch (SQLException e) {
            System.err.println("[ERROR] Query exception : " + e.getMessage());

        }

        return hasSucceeded;
    }

    @Override
    public boolean delete(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(User user) {
        return delete(user.getId());
    }

    @Override
    public boolean update(User user) {
        throw new UnsupportedOperationException();
    }

    private User getUserFromRSet(ResultSet resultSet) {
        User user = null;

        try {
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String pseudo = resultSet.getString("pseudo");
                String email = resultSet.getString("email");
                Date inscriptionDate = new Date(resultSet.getDate("inscriptionDate").getTime());

                user = new User();

                user.setId(id);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPseudo(pseudo);
                user.setEmail(email);
                user.setInscriptionDate(inscriptionDate);
            }

        } catch (SQLException e) {
            System.err.println("[ERROR] getUserFromRSet : " + e.getMessage());

        }

        return user;
    }
}
