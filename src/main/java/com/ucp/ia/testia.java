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
     public static void main(String[] args) throws Exception {

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
         KohonenThread kt = new KohonenThread(ingredientsstarter,recipestarter,starterName,"starter");
         KohonenThread kt2 = new KohonenThread(ingredientsmain,recipesmain,mainCoursesName,"mainCourses");
         KohonenThread kt3 = new KohonenThread(ingredientsmain,recipesmain,mainCoursesName,"dessert");

         kt.start();
         kt2.start();
         kt3.start();

         kt.join();
         kt2.join();
         kt3.join();

         Kohonen kohonen = kt.getKohonen();
         Kohonen kohonen2 = kt2.getKohonen();
         Kohonen kohonen3 = kt3.getKohonen();


         String data="CatID;DIStID;RecetteID;RecetteDist \n";
         try {
             BufferedWriter writer3 = new BufferedWriter(new FileWriter("./src/main/resources/SortiIA.csv"));

             int indexcat=0;
             for(Categorie cat : kohonen.getCluster()) {
                 data=data+indexcat+";starter;";
                 for (int indexdisp = 0; indexdisp < kohonen2.getCluster().size(); indexdisp++) {
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
                 for (int indexdisp = 0; indexdisp < kohonen2.getCluster().size(); indexdisp++) {
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
                 data = data + indexcat + ";dessert;";
                 for (int indexdisp = 0; indexdisp < kohonen2.getCluster().size(); indexdisp++) {
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
