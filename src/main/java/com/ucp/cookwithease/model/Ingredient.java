package com.ucp.cookwithease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    // Used by the database
    private int id;
    private int recipeID;

    private String name;
    private float quantity;
    private String unit;

    @Override
    public String toString() {
        return "    - " + name + " (" + quantity + (unit != null ? " " + unit : "") + ")";
    }
}
