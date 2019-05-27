package com.ucp.initproject;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.dao.general.Index;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.scraper_updated.engine.ScrapeToDatabase;
import com.ucp.scraper_updated.preprocessing.csv.ReaderCsv;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;


public class InitializeProject {
    private static final int MAX_RECIPES = 1000;
    private static final String FILE_NAME ="src/main/resources/urlsBon.csv";
    private static File file = new File(FILE_NAME);

    public static void main(String[] args) {
        // Filling the database
        ReaderCsv readerCsv = new ReaderCsv(file);
        ScrapeToDatabase scrapeToDatabase = new ScrapeToDatabase();
        scrapeToDatabase.execute(MAX_RECIPES, readerCsv.reader());

        // Creating the Lucene index
        Index index = DAOFactory.getIndex();
        index.create();

        LinkedList<Recipe> recipesToIndex = DAOFactory.getRecipeDAO().findAll();
        index.addRecipes(recipesToIndex);
        index.close();
    }
}
