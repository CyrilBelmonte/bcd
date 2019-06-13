package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.DiscoverSuggestions;
import com.ucp.cookwithease.model.DishType;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;
import com.ucp.xml.exist.query.QueryCategory;
import com.ucp.xml.exist.query.QueryProfile;
import com.ucp.xml.exist.query.thread.profiles.ThreadList;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;


public class SuggestionEngine {
    private static QueryCategory queryCategory = new QueryCategory();
    private static QueryProfile queryProfile = new QueryProfile();

    private SuggestionEngine() {}

    public static LinkedList<Recipe> getRecipesSuggestion(int recipeID) {
        LinkedList<Integer> recipesID = new LinkedList<>(
                queryCategory.findRecipe(recipeID));

        recipesID.remove((Integer) recipeID);

        return DAOFactory.getRecipeDAO().findAll(recipesID);
    }

    public static LinkedList<User> getFriendsSuggestion(User user) {
        LinkedList<Integer> usersID = new LinkedList<>(
            queryProfile.getIdUsersByIdUser(user.getId()));

        usersID.remove((Integer) user.getId());
        usersID.removeAll(user.getFriends());

        return DAOFactory.getUserDAO().findAll(usersID);
    }

    public static DiscoverSuggestions getDiscoverSuggestion(User user, DishType type) {
        ThreadList profile = new ThreadList(user.getId(), type.toString().toLowerCase());

        List<Integer> recipesCatFromUser = profile.getCategory().get("user");
        List<Integer> recipesCatFromFriends = profile.getCategory().get("friend");
        List<Integer> recipesCatFromProfiles = profile.getCategory().get("profile");

        LinkedList<Recipe> recipesBasedOnUser = getRecipesFromCategories(
            new LinkedList<>(recipesCatFromUser), 6, 15);

        LinkedList<Recipe> recipesBasedOnFriends = getRecipesFromCategories(
            new LinkedList<>(recipesCatFromFriends), 3, 6);

        LinkedList<Recipe> recipesBasedOnProfiles = getRecipesFromCategories(
            new LinkedList<>(recipesCatFromProfiles), 3, 6);

        DiscoverSuggestions discoverSuggestions = new DiscoverSuggestions(
            recipesBasedOnUser, recipesBasedOnFriends, recipesBasedOnProfiles);

        return discoverSuggestions;
    }

    public static LinkedList<Recipe> getMenuSuggestion(User user, DishType type) {
        ThreadList profile = new ThreadList(user.getId(), type.toString().toLowerCase());

        List<Integer> recipesCatFromUser = profile.getCategory().get("user");
        List<Integer> recipesCatFromFriends = profile.getCategory().get("friend");
        List<Integer> recipesCatFromProfiles = profile.getCategory().get("profile");

        LinkedList<Recipe> recipesBasedOnUser = getRecipesFromCategories(
            new LinkedList<>(recipesCatFromUser), 3, 14);

        LinkedList<Recipe> recipesBasedOnFriends = getRecipesFromCategories(
            new LinkedList<>(recipesCatFromFriends), 2, 4);

        LinkedList<Recipe> recipesBasedOnProfiles = getRecipesFromCategories(
            new LinkedList<>(recipesCatFromProfiles), 2, 4);

        if (recipesBasedOnFriends.size() > 0 && recipesBasedOnProfiles.size() > 0) {
            while (recipesBasedOnFriends.size() > 2) {
                recipesBasedOnFriends.removeLast();
            }

            while (recipesBasedOnProfiles.size() > 2) {
                recipesBasedOnProfiles.removeLast();
            }
        }

        LinkedHashSet<Recipe> uniqueSuggestions = new LinkedHashSet<>();
        uniqueSuggestions.addAll(recipesBasedOnUser);
        uniqueSuggestions.addAll(recipesBasedOnFriends);
        uniqueSuggestions.addAll(recipesBasedOnProfiles);

        LinkedList<Recipe> suggestions = new LinkedList<>(uniqueSuggestions);
        Collections.shuffle(suggestions);

        while (recipesBasedOnProfiles.size() > 14) {
            suggestions.removeLast();
        }

        return suggestions;
    }

    private static LinkedList<Recipe> getRecipesFromCategories(
            LinkedList<Integer> categoriesID, int maxRecipesByCategory, int maxRecipes) {

        int categoryID;
        int maxResults;

        LinkedList<Integer> recipesID;
        LinkedList<Integer> randomizedRecipesID;

        LinkedList<Integer> suggestedRecipesID = new LinkedList<>();

        for (int i = 0; i < categoriesID.size() && suggestedRecipesID.size() < maxRecipes; i++) {
            maxResults = Math.min(maxRecipes - suggestedRecipesID.size(), maxRecipesByCategory);

            categoryID = categoriesID.get(i);
            recipesID = new LinkedList<>(queryCategory.getRecipeByCat(categoryID));

            randomizedRecipesID = randomizeList(recipesID, maxResults);
            suggestedRecipesID.addAll(randomizedRecipesID);
        }

        Collections.shuffle(suggestedRecipesID);

        return DAOFactory.getRecipeDAO().findAll(suggestedRecipesID);
    }

    private static LinkedList<Integer> randomizeList(LinkedList<Integer> sourceList, int maxResults) {
        int pickedIndex;
        int pickedElement;

        LinkedList<Integer> randomizedList = new LinkedList<>();
        LinkedList<Integer> unpickedElements = new LinkedList<>(sourceList);

        for (int i = 0; i < unpickedElements.size() && i < maxResults; i++) {
            pickedIndex = (int) (Math.random() * (unpickedElements.size() - 1));
            pickedElement = unpickedElements.remove(pickedIndex);
            randomizedList.addLast(pickedElement);
        }

        return randomizedList;
    }
}
