package com.ucp.ia.THreaddist;

import com.ucp.cookwithease.model.Recipe;
import com.ucp.ia.Kohonen;

import java.util.LinkedList;

public class KohonenThreaddist extends  Thread {

    LinkedList<String> TitleList;
    private LinkedList<String> ingredients;
    private LinkedList<Recipe> recipes ;
    private  KohonenDist kohonen;
    private String type;
    public KohonenThreaddist(LinkedList<String> ingredients, LinkedList<Recipe> recipes, LinkedList<String> TitleList, String type) {
        this.TitleList=TitleList;
        this.recipes=recipes;
        this.ingredients=ingredients;
        this.type=type;
    }

    public void run(){
        System.out.println("Init kohonen"+type+"start");
        kohonen=new KohonenDist(ingredients,recipes,TitleList);
        System.out.println(" Init kohonen "+type+"end");
       System.out.println("Clustering "+type+"start");
       kohonen.Clustering();
        System.out.println("Clustering "+type+"end");
    }

    public KohonenDist getKohonen() {
        return kohonen;
    }

    public void setKohonen(KohonenDist kohonen) {
        this.kohonen = kohonen;
    }
}
