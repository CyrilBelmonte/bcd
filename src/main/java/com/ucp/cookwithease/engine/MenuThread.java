package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.model.DishType;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;

import java.util.LinkedList;


public class MenuThread extends Thread {
    private User user;
    private DishType type;
    private LinkedList<Recipe> suggestedRecipes;

    public MenuThread(User user, DishType type) {
        this.user = user;
        this.type = type;
    }

    @Override
    public void run() {
        suggestedRecipes = SuggestionEngine.getMenuSuggestion(user, type);
    }

    public LinkedList<Recipe> getSuggestedRecipes() {
        return suggestedRecipes;
    }
}
