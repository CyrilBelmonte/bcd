package com.ucp.initproject;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.dao.general.Index;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.scraper_updated.engine.ScrapeToDatabase;

import java.util.LinkedList;


public class InitializeProject {
    private static final int MAX_RECIPES = 1000;

    public static void main(String[] args) {
        // Filling the database
        ScrapeToDatabase scrapeToDatabase = new ScrapeToDatabase();
        scrapeToDatabase.execute(MAX_RECIPES);

        // Creating the Lucene index
        Index index = DAOFactory.getIndex();
        index.create();

        LinkedList<Recipe> recipesToIndex = DAOFactory.getRecipeDAO().findAll();
        index.addRecipes(recipesToIndex);
        index.close();
    }
}
