package com.ucp.cookwithease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.LinkedList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    // Used by the database
    private int id;

    // Not loaded (only for update)
    private String password = null;

    private String firstName;
    private String lastName;
    private String pseudo;
    private String email;
    private Date inscriptionDate;

    private LinkedList<Recipe> bookmarks = new LinkedList<>();
    private LinkedList<User> friends = new LinkedList<>();
    private LinkedList<Comment> comments = new LinkedList<>();
}
