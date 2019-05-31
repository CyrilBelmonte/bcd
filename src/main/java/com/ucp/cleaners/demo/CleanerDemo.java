package com.ucp.cleaners.demo;

import com.ucp.ai_experiments.AIEntries;
import com.ucp.cleaners.AITools;

import java.util.LinkedList;


public class CleanerDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<String> startersName = AIEntries.getPartsOfStartersName();
        LinkedList<String> mainCoursesName = AIEntries.getPartsOfMainCoursesName();
        LinkedList<String> dessertsName = AIEntries.getPartsOfDessertsName();

        LinkedList<String> startersIngredient = AIEntries.getAllStartersIngredients();
        LinkedList<String> mainCoursesIngredients = AIEntries.getAllMainCoursesIngredients();
        LinkedList<String> dessertsIngredients = AIEntries.getAllDessertsIngredients();


        System.out.println(AITools.contains("Tarte au poulet!", "poulet"));
        System.out.println(AITools.normalizeQuantity(5, "ml"));
        System.out.println(AITools.normalizeQuantity(5, "tasses"));
        System.out.println(AITools.isUnitValid("ml"));
        System.out.println(AITools.isUnitValid("cuillères à soupe"));
        System.out.println(AITools.isUnitValid("tasses"));
        System.out.println(AITools.isUnitValid("pot"));

        System.out.println(AIEntries.getMaxIngredientsQuantities());


        System.err.println("*** STARTERS NAME ***");

        for (String starterName : startersName) {
            System.out.println(starterName);

            System.out.flush();
            Thread.sleep(10);
        }

        /*
        System.err.println("\n*** STARTERS INGREDIENT ***");

        for (String starterIngredient : startersIngredient) {
            System.out.println(starterIngredient);

            System.out.flush();
            Thread.sleep(10);
        }
        */


        System.err.println("\n*** MAIN COURSES NAME ***");

        for (String mainCourseName : mainCoursesName) {
            System.out.println(mainCourseName);

            System.out.flush();
            Thread.sleep(10);
        }

        /*
        System.err.println("\n*** MAIN COURSES INGREDIENT ***");

        for (String mainCourseIngredients : mainCoursesIngredients) {
            System.out.println(mainCourseIngredients);

            System.out.flush();
            Thread.sleep(10);
        }
        */


        System.err.println("\n*** DESSERTS NAME ***");

        for (String dessertName : dessertsName) {
            System.out.println(dessertName);

            System.out.flush();
            Thread.sleep(10);
        }

        /*
        System.err.println("\n*** DESSERTS INGREDIENT ***");

        for (String dessertIngredients : dessertsIngredients) {
            System.out.println(dessertIngredients);

            System.out.flush();
            Thread.sleep(10);
        }
        */
    }
}
