package com.ucp.cookwithease.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {
    private int id;
    private String name;
    private int duration;
    private int persons;
    private DishType type;
    private Level cost;
    private Level difficulty;
    private double rating;
    private String picture;

    private LinkedList<Ingredient> ingredients = new LinkedList<>();
    private LinkedList<Step> steps = new LinkedList<>();
}
