package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.SearchForm;
import com.ucp.cookwithease.model.Recipe;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.LinkedList;


public class SearchPage extends Page<SearchForm> {
    private static final int SEARCH_MAX_RESULTS = 45;

    public SearchPage(HttpServletRequest request) {
        super(request);

        form = new SearchForm(request);
    }

    public boolean loadRecipes() {
        LinkedList<Recipe> recipes = DAOFactory.getRecipeDAO().findAll(
            DAOFactory.getIndex().findRecipesId("*:*", SEARCH_MAX_RESULTS));

        Collections.shuffle(recipes);

        if (recipes.size() == 0) {
            form.addGlobalError("Aucune recette à afficher");

            return false;

        } else {
            request.setAttribute("recipes", recipes);

            return true;
        }
    }

    public boolean search() {
        String keywords = form.getSearchedKeywords();

        LinkedList<Integer> recipesID;
        LinkedList<Recipe> recipes;

        if (keywords == null) {
            return loadRecipes();
        }

        recipesID = DAOFactory.getIndex().findRecipesId(keywords, SEARCH_MAX_RESULTS);
        recipes = DAOFactory.getRecipeDAO().findAll(recipesID);

        if (recipes.size() == 0) {
            form.addGlobalError("Aucune recette ne correspond à vos critères de recherche");

            return false;

        } else {
            request.setAttribute("recipes", recipes);

            return true;
        }
    }
}
