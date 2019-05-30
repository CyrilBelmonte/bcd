package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.AssistantForm;
import com.ucp.cookwithease.model.Day;
import com.ucp.cookwithease.model.Recipe;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.LinkedList;


public class AssistantPage extends Page<AssistantForm> {
    public AssistantPage(HttpServletRequest request) {
        super(request);

        form = new AssistantForm(request);
    }

    public boolean loadMenu() {
        LinkedList<String> daysName = new LinkedList<>(Arrays.asList(
            "Lundi", "Mardi", "Mercredi", "Jeudi", "Vendredi", "Samedi", "Dimanche"
        ));

        int maxDays = daysName.size();

        Day day;
        LinkedList<Day> days = new LinkedList<>();

        for (String dayName : daysName) {
            day = new Day();
            day.setName(dayName);

            days.addLast(day);
        }

        // ------------------- A REMPLACER -------------------
        // ... récupérer entrées suggérées
        LinkedList<Recipe> suggestedStarters = DAOFactory.getRecipeDAO().findAllStarters();

        // ... récupérer plats suggérés
        LinkedList<Recipe> suggestedMainCourses = DAOFactory.getRecipeDAO().findAllMainCourses();

        // ... récupérer desserts suggérés
        LinkedList<Recipe> suggestedDesserts = DAOFactory.getRecipeDAO().findAllDesserts();
        // ----------------------------------------------------

        if (suggestedStarters.size() < maxDays * 2 ||
            suggestedMainCourses.size() < maxDays * 2 ||
            suggestedDesserts.size() < maxDays * 2) {

            form.addGlobalError("Le nombre de recettes pertinentes est trop faible pour construire le menu");

            return false;
        }

        int nextRecipeID = 0;

        for (int i = 0; i < maxDays; i++) {
            day = days.get(i);

            day.addLunchRecipe(suggestedStarters.get(nextRecipeID));
            day.addLunchRecipe(suggestedMainCourses.get(nextRecipeID));
            day.addLunchRecipe(suggestedDesserts.get(nextRecipeID));

            day.addDinnerRecipe(suggestedStarters.get(nextRecipeID + 1));
            day.addDinnerRecipe(suggestedMainCourses.get(nextRecipeID + 1));
            day.addDinnerRecipe(suggestedDesserts.get(nextRecipeID + 1));

            nextRecipeID += 2;
        }

        request.setAttribute("days", days);

        return true;
    }
}
