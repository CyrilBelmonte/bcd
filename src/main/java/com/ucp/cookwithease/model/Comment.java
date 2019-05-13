package com.ucp.cookwithease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    // Used by the database
    private int userID;
    private int recipeID;

    // Used by the website (recipe & profile pages)
    private String pseudo;
    private String recipeName;

    private String description;
    private int rating;
    private Date publicationDate;
}
