package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.DiscoverForm;
import com.ucp.cookwithease.model.Recipe;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;


public class DiscoverPage extends Page<DiscoverForm> {
    private static final int SEARCH_MAX_RESULTS = 24;

    public DiscoverPage(HttpServletRequest request) {
        super(request);

        form = new DiscoverForm(request);
    }

    public boolean loadStarters() {
        LinkedList<Recipe> recipes = DAOFactory.getRecipeDAO().findAll(SEARCH_MAX_RESULTS);

        if (recipes.size() == 0) {
            form.addGlobalError("Aucune recette à afficher.");

            return false;

        } else {
            request.setAttribute("recipes", recipes);

            return true;
        }
    }

    public boolean loadMainCourses() {
        LinkedList<Recipe> recipes = DAOFactory.getRecipeDAO().findAll(SEARCH_MAX_RESULTS);

        if (recipes.size() == 0) {
            form.addGlobalError("Aucune recette à afficher.");

            return false;

        } else {
            request.setAttribute("recipes", recipes);

            return true;
        }
    }

    public boolean loadDesserts() {
        LinkedList<Recipe> recipes = DAOFactory.getRecipeDAO().findAll(SEARCH_MAX_RESULTS);

        if (recipes.size() == 0) {
            form.addGlobalError("Aucune recette à afficher.");

            return false;

        } else {
            request.setAttribute("recipes", recipes);

            return true;
        }
    }
}
