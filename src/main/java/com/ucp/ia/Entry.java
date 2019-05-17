
package com.ucp.ia;

import com.ucp.cookwithease.model.*;


import java.util.LinkedList;

public class Entry {
    private LinkedList<IngredientsWeight> data;
    private LinkedList<IngredientsWeight> datatitle;
    private String RecipeName;


    public String getRecipeName() {
        return RecipeName;
    }

    public void setRecipeName(String recipeName) {
        RecipeName = recipeName;
    }

    public Entry(LinkedList<IngredientsWeight> data, LinkedList<IngredientsWeight> datatitle, String recipeName) {
        this.data = data;
        this.datatitle = datatitle;
        this.RecipeName=recipeName;
    }

    public LinkedList<IngredientsWeight> getData() {
        return data;
    }

    public void setData(LinkedList<IngredientsWeight> data) {
        this.data = data;
    }

    public LinkedList<IngredientsWeight> getDatatitle() {
        return datatitle;
    }

    public void setDatatitle(LinkedList<IngredientsWeight> datatitle) {
        this.datatitle = datatitle;
    }

}

