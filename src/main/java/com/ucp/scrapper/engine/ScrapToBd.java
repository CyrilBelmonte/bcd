package com.ucp.scrapper.engine;


import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.*;


public class ScrapToBd {

    public void execute(int nbr) {

        for (int i = 0; i < nbr; i++) {
            String url = "https://marmiton.org/recettes/recette-hasard.aspx";
            Parser parser = new Parser(url);
            if (parser.getRecipe() == null) {
                continue;
            }
            System.out.println(parser.getRecipe());

            Recipe recipeDAO = new Recipe();

            recipeDAO.setName(parser.getRecipe().getTitle());
            recipeDAO.setDuration(parser.getRecipe().getRecipeTime());
            recipeDAO.setPersons(parser.getRecipe().getNumberPersons());
            recipeDAO.setType(getDishType(parser.getRecipe().getTags()));
            recipeDAO.setCost(getLevelCostFromWeb(parser.getRecipe().getEconomicLevel()));
            recipeDAO.setDifficulty(getLevelDiffFromWeb(parser.getRecipe().getDifficultyLevel()));
            recipeDAO.setRating(parser.getRecipe().getMark());
            recipeDAO.setPicture(parser.getRecipe().getPicture());

            for (int index = 0; index < parser.getRecipe().getIngredients().size(); index++) {

                Ingredient ingredient = new Ingredient();
                ingredient.setName((parser.getRecipe().getIngredients().get(index).getName()));
                ingredient.setQuantity(parser.getRecipe().getIngredients().get(index).getIngQuantities());
                ingredient.setUnit(parser.getRecipe().getIngredients().get(index).getUnits());

                recipeDAO.addIngredient(ingredient);
            }

            for (int index2 = 0; index2 < parser.getRecipe().getSteps().size(); index2++) {

                Step stepDAO = new Step();
                stepDAO.setPosition(parser.getRecipe().getSteps().get(index2).getStepNumber());
                stepDAO.setDescription(parser.getRecipe().getSteps().get(index2).getDescription());

                recipeDAO.addStep(stepDAO);
            }

            DAOFactory.getRecipeDAO().insert(recipeDAO);

        }

    }

    public Level getLevelDiffFromWeb(String chaine) {
        if (chaine.toLowerCase().equals("niveau moyen")) {
            return Level.AVERAGE;
        } else if (chaine.toLowerCase().equals("difficile")) {
            return Level.HIGH;
        } else {
            return Level.LOW;
        }
    }

    public Level getLevelCostFromWeb(String chaine) {
        if (chaine.toLowerCase().equals("coût moyen")) {
            return Level.AVERAGE;
        } else if (chaine.toLowerCase().equals("assez cher")) {
            return Level.HIGH;
        } else {
            return Level.LOW;
        }
    }

    public DishType getDishType(String chaine) {
        if (chaine.toLowerCase().contains("plat")) {
            return DishType.MAIN_COURSE;
        } else if (chaine.toLowerCase().contains("dessert")) {
            return DishType.DESSERT;
        } else if (chaine.toLowerCase().contains("entrée")) {
            return DishType.STARTER;
        } else {
            return DishType.OTHER;
        }
    }
}
