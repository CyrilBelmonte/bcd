package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.forms.AssistantForm;
import com.ucp.cookwithease.model.Day;
import com.ucp.cookwithease.model.DishType;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        MenuThread suggestedStartersThread = new MenuThread(user, DishType.STARTER);
        MenuThread suggestedMainCoursesThread = new MenuThread(user, DishType.MAIN_COURSE);
        MenuThread suggestedDessertsThread = new MenuThread(user, DishType.DESSERT);

        try {
            suggestedStartersThread.start();
            suggestedMainCoursesThread.start();
            suggestedDessertsThread.start();

            suggestedStartersThread.join();
            suggestedMainCoursesThread.join();
            suggestedDessertsThread.join();

        } catch (InterruptedException e) {
            // Nothing
        }

        LinkedList<Recipe> suggestedStarters = suggestedStartersThread.getSuggestedRecipes();
        LinkedList<Recipe> suggestedMainCourses = suggestedMainCoursesThread.getSuggestedRecipes();
        LinkedList<Recipe> suggestedDesserts = suggestedDessertsThread.getSuggestedRecipes();

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
