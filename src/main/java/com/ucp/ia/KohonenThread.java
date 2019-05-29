package com.ucp.ia;

import com.ucp.cookwithease.model.Recipe;

import java.util.LinkedList;

public class KohonenThread extends  Thread {

    LinkedList<String> TitleList;
    private LinkedList<String> ingredients;
    private LinkedList<Recipe> recipes ;
    private  Kohonen kohonen;
    private String type;
    public KohonenThread(LinkedList<String> ingredients, LinkedList<Recipe> recipes, LinkedList<String> TitleList, String type) {
        this.TitleList=TitleList;
        this.recipes=recipes;
        this.ingredients=ingredients;
        this.type=type;
    }

    public void run(){
        System.out.println("Init kohonen"+type+"start");
        kohonen=new Kohonen(ingredients,recipes,TitleList);
        System.out.println(" Init kohonen "+type+"end");
       System.out.println("Clustering "+type+"start");
       kohonen.Clustering();
        System.out.println("Clustering "+type+"end");
    }

    public Kohonen getKohonen() {
        return kohonen;
    }

    public void setKohonen(Kohonen kohonen) {
        this.kohonen = kohonen;
    }
}
