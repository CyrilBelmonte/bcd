package com.ucp.ia;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Ingredient;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.recipecleaner.AIEntries;

import java.util.LinkedList;

public class testia {
     public static void main(String[] args) {

         LinkedList<Recipe> recipes = DAOFactory.getRecipeDAO().findAllMainCourses();
         LinkedList<String> ingredientsall = new LinkedList<>();
         ingredientsall = AIEntries.getAllMainCoursesIngredients();
         LinkedList<String> mainCoursesName = AIEntries.getPartsOfMainCoursesName();
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
            Kohonen kohonen = new Kohonen(ingredientsall,recipes,mainCoursesName);
                    kohonen.Clustering();
                    for(Categorie cat : kohonen.getCluster()) {
                        System.out.println("**************** NEXT CATEGORIE ****************");
                        for (Recipe recipe : cat.getRecipes()) {
                            System.out.println(recipe.getName());
                        }
                    }
    }
}
