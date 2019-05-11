package com.ucp.cookwithease.forms;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Recipe;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;


public class SearchForm extends Form {
    private static final String SEARCH_FIELD = "search";
    private static final int SEARCH_LENGTH = 100;
    private static final int SEARCH_MAX_RESULTS = 45;

    public LinkedList<Recipe> searchFromKeywords(String keywords) {
        LinkedList<Integer> recipesID;
        LinkedList<Recipe> recipes;

        if (keywords == null) {
            recipes = DAOFactory.getRecipeDAO().findAll(SEARCH_MAX_RESULTS);

        } else {
            recipesID = DAOFactory.getIndex().findRecipesId(keywords, SEARCH_MAX_RESULTS);
            recipes = DAOFactory.getRecipeDAO().findAll(recipesID);
        }

        if (recipes.size() == 0) {
            this.addError("global", "Aucune recette ne correspond à vos critères de recherche.");
        }

        return recipes;
    }

    public LinkedList<Recipe> search(HttpServletRequest request) {
        String keywords = getValueFrom(request, SEARCH_FIELD, SEARCH_LENGTH);

        return searchFromKeywords(keywords);
    }

    public LinkedList<Recipe> searchAll() {
        return searchFromKeywords(null);
    }
}
