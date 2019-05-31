package com.ucp.ia.THreaddist;

import com.ucp.ai_experiments.AIEntries;
import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.ia.Categorie;
import com.ucp.ia.Kohonen;
import com.ucp.ia.KohonenThread;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class testiathreaddist {
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
         KohonenThreaddist kt = new KohonenThreaddist(ingredientsstarter,recipestarter,starterName,"starter");
        KohonenThreaddist kt2 = new KohonenThreaddist(ingredientsmain,recipesmain,mainCoursesName,"mainCourses");
         KohonenThreaddist kt3 = new KohonenThreaddist(ingredientsdessert,recipesdessert,dessertName,"dessert");

        kt.start();
         kt2.start();
         kt3.start();



          kt.join();
         kt2.join();
         kt3.join();

         KohonenDist kohonen = kt.getKohonen();
         KohonenDist kohonen2 = kt2.getKohonen();
         KohonenDist kohonen3 = kt3.getKohonen();


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
                         data = data + cat.getRecipes().get(indexdisp).getId()+ ";" + cat.getDistance().get(indexdisp) + ";";
                     }
                 }
                 data = data + "\n";
                 indexcat++;
             }
             print.write(data);
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
