package com.ucp.ia;

import com.ucp.cookwithease.model.Recipe;

import java.util.LinkedList;

public class KohonenThread extends  Thread {



    private  Kohonen kohonen;
    private String type;
    public KohonenThread(LinkedList<String> ingredients, LinkedList<Recipe> recipes, LinkedList<String> TitleList, String type) {
        kohonen=new Kohonen(ingredients,recipes,TitleList);
        this.type=type;
    }

    public void run(){
       System.out.println("Clustering kohonen "+type);
       kohonen.Clustering();
        System.out.println("Clustering end "+type);
    }

    public Kohonen getKohonen() {
        return kohonen;
    }

    public void setKohonen(Kohonen kohonen) {
        this.kohonen = kohonen;
    }
}
