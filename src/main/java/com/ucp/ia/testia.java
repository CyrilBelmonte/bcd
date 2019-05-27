package com.ucp.ia;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Ingredient;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.recipecleaner.AIEntries;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

public class testia {
     public static void main(String[] args) {

         LinkedList<Recipe> recipesmain = DAOFactory.getRecipeDAO().findAllMainCourses();
         LinkedList<Recipe> recipesdessert = DAOFactory.getRecipeDAO().findAllDesserts();
         LinkedList<Recipe> recipestarter = DAOFactory.getRecipeDAO().findAllStarters();

         LinkedList<String> ingredientsmain = new LinkedList<>();
         ingredientsmain = AIEntries.getAllMainCoursesIngredients();
         LinkedList<String> ingredientsdessert = new LinkedList<>();
         ingredientsdessert = AIEntries.getAllDessertsIngredients();
         LinkedList<String> ingredientsstarter = new LinkedList<>();
         ingredientsstarter = AIEntries.getAllMainCoursesIngredients();
         LinkedList<String> mainCoursesName = AIEntries.getPartsOfMainCoursesName();
         LinkedList<String> dessertName = AIEntries.getPartsOfDessertsName();
         LinkedList<String> starterName = AIEntries.getPartsOfDessertsName();
         /*
         for(Recipe recipe : recipes) {
             LinkedList<Ingredient> ingredients = DAOFactory.getIngredientDAO().findAll(recipe);
             ingredientsall.addAll(ingredients);
         }
        */
         /*
         System.out.println("**************** ALL RECIPES ****************");

         for (Recipe recipe : recipes) {
             System.out.println(recipe);
         }
         System.out.println("**************** ALL INGREDIENT ****************");
         for(Ingredient ingredient : ingredientsall)
         {
             System.out.println(ingredient);
         }*/
            Kohonen kohonen = new Kohonen(ingredientsstarter,recipestarter,starterName);
                    kohonen.Clustering();

         Kohonen kohonen2 = new Kohonen(ingredientsmain,recipesmain,mainCoursesName);
         kohonen2.Clustering();
         Kohonen kohonen3 = new Kohonen(ingredientsdessert,recipesdessert,dessertName);
         kohonen3.Clustering()
         ;

         String data="CatID;DIStID;RecetteID;RecetteDist \n";
         try {
             BufferedWriter writer3 = new BufferedWriter(new FileWriter("./src/main/resources/SortiIA.csv"));

             int indexcat=0;
             for(Categorie cat : kohonen.getCluster()) {
                 data=data+indexcat+";starter;";
                 for (int indexdisp = 0; indexdisp < 100; indexdisp++) {
                     data = data + cat.getDistanceCat(indexdisp) + ";";
                 }
                 if (!cat.getRecipes().isEmpty()) {
                     for (int indexdisp = 0; indexdisp < cat.getRecipes().size(); indexdisp++) {
                         data = data + cat.getRecipes().get(indexdisp).getId() + ";" + cat.getDistance().get(indexdisp) + ";";
                     }
                 }
                 data = data + "\n";
                 indexcat++;
             }

             for(Categorie cat : kohonen2.getCluster()) {
                 data=data+indexcat+";main_Courses;";
                 for (int indexdisp = 0; indexdisp < 100; indexdisp++) {
                     data = data + cat.getDistanceCat(indexdisp) + ";";
                 }
                 if (!cat.getRecipes().isEmpty()) {
                     for (int indexdisp = 0; indexdisp < cat.getRecipes().size(); indexdisp++) {
                         data = data + cat.getRecipes().get(indexdisp).getId() + ";" + cat.getDistance().get(indexdisp) + ";";
                     }
                 }

                 data = data + "\n";
                 indexcat++;
             }
             for(Categorie cat : kohonen3.getCluster()) {
                 data=data+indexcat+";dessert;";
                 for (int indexdisp = 0; indexdisp < 100; indexdisp++) {
                     data = data + cat.getDistanceCat(indexdisp) + ";";
                 }
                 if (!cat.getRecipes().isEmpty()) {
                     for (int indexdisp = 0; indexdisp < cat.getRecipes().size(); indexdisp++) {
                         data = data + cat.getRecipes().get(indexdisp).getId() + ";" + cat.getDistance().get(indexdisp) + ";";
                     }
                 }
                 data = data + "\n";
                 indexcat++;
             }
             writer3.write(data);
             writer3.close();
         } catch (IOException ex) {
             ex.printStackTrace();
         }


                    /*
                    for(Categorie cat : kohonen.getCluster()) {
                        System.out.println("**************** NEXT CATEGORIE ****************");
                        for (Recipe recipe : cat.getRecipes()) {
                            System.out.println(recipe.getName());
                        }
                    }
                    */

    }
}
