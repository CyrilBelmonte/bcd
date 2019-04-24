package com.ucp.cookwithease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {
    private Recipe recipe;

    private int id;
    private String name;
    private double quantity;
}
