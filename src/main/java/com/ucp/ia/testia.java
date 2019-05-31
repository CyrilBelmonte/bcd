package com.ucp.ia;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Ingredient;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.recipecleaner.AIEntries;
import javafx.print.Printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
         ingredientsstarter = AIEntries.getAllStartersIngredients();
         LinkedList<String> mainCoursesName = AIEntries.getPartsOfMainCoursesName();
         LinkedList<String> dessertName = AIEntries.getPartsOfDessertsName();
         LinkedList<String> starterName = AIEntries.getPartsOfStartersName();
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
         KohonenThread kt3 = new KohonenThread(ingredientsdessert,recipesdessert,dessertName,"dessert");

        kt.start();
         kt2.start();
         kt3.start();



          kt.join();
         kt2.join();
         kt3.join();

         Kohonen kohonen = kt.getKohonen();
         Kohonen kohonen2 = kt2.getKohonen();
         Kohonen kohonen3 = kt3.getKohonen();


       //  String data="CatID;DIStID;RecetteID;RecetteDist \n";

         try {
             BufferedWriter writer3 = new BufferedWriter(new FileWriter("./src/main/resources/SortiIA.csv"));
             PrintWriter print = new PrintWriter(writer3);
                String data ="";
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
             String data2 ="";
             for(Categorie cat : kohonen2.getCluster()) {
                 data2=data2+indexcat+";main_Courses;";
                 for (int indexdisp = 0; indexdisp < kohonen2.getCluster().size(); indexdisp++) {
                     data2 = data2 + cat.getDistanceCat(indexdisp) + ";";
                 }
                 if (!cat.getRecipes().isEmpty()) {
                     for (int indexdisp = 0; indexdisp < cat.getRecipes().size(); indexdisp++) {
                         data2 = data2 + cat.getRecipes().get(indexdisp).getId() + ";" + cat.getDistance().get(indexdisp) + ";";
                     }
                 }

                 data2 = data2 + "\n";
                 indexcat++;
             }
             String data3 ="";
             for(Categorie cat : kohonen3.getCluster()) {
                 data3 = data3 + indexcat + ";dessert;";
                 for (int indexdisp = 0; indexdisp < kohonen2.getCluster().size(); indexdisp++) {
                     data3 = data3 + cat.getDistanceCat(indexdisp) + ";";
                 }
                 if (!cat.getRecipes().isEmpty()) {
                     for (int indexdisp = 0; indexdisp < cat.getRecipes().size(); indexdisp++) {
                         data3 = data3 + cat.getRecipes().get(indexdisp).getId()+ ";" + cat.getDistance().get(indexdisp) + ";";
                     }
                 }
                 data3 = data3 + "\n";
                 indexcat++;
             }
             print.write(data);
             print.println(data2);
             print.println(data3);
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
