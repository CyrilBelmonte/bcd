package com.ucp.cookwithease.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;


@Data
@AllArgsConstructor
public class DiscoverSuggestions {
    private LinkedList<Recipe> recipesBasedOnUser;
    private LinkedList<Recipe> recipesBasedOnFriends;
    private LinkedList<Recipe> recipesBasedOnProfiles;
}
