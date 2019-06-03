package com.ucp.ai;

import java.util.LinkedList;


public class Entry {
    private LinkedList<IngredientsWeight> data;
    private LinkedList<IngredientsWeight> dataTitles;
    private String recipeName;

    public Entry(LinkedList<IngredientsWeight> data, LinkedList<IngredientsWeight> dataTitles, String recipeName) {
        this.data = data;
        this.dataTitles = dataTitles;
        this.recipeName = recipeName;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public LinkedList<IngredientsWeight> getData() {
        return data;
    }

    public void setData(LinkedList<IngredientsWeight> data) {
        this.data = data;
    }

    public LinkedList<IngredientsWeight> getDataTitles() {
        return dataTitles;
    }

    public void setDataTitles(LinkedList<IngredientsWeight> dataTitles) {
        this.dataTitles = dataTitles;
    }
}
