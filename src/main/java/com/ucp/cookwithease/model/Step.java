package com.ucp.cookwithease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Step {
    // Used by the database
    private int id;
    private int recipeID;

    private int position;
    private String description;
}
