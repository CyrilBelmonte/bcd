package com.ucp.xml.parse_xml.parser_csv.dao.category;

import java.util.HashMap;

public class SimpleCategory implements Category {

    private HashMap<Integer, Float> catDist;
    private HashMap<Integer, Float> recDist;
    private String idOfCat;

    @Override
    public HashMap<Integer, Float> getCatDist() {
        return catDist;
    }

    public void setCatDist(HashMap<Integer, Float> catDist) {
        this.catDist = catDist;
    }

    @Override
    public HashMap<Integer, Float> getRecDist() {
        return recDist;
    }

    public void setRecDist(HashMap<Integer, Float> recDist) {
        this.recDist = recDist;
    }

    @Override
    public String getIdOfCat() {
        return idOfCat;
    }

    public void setIdOfCat(String idOfCat) {
        this.idOfCat = idOfCat;
    }

    @Override
    public String getTypeOfCat() {
        return typeOfCat;
    }

    public void setTypeOfCat(String typeOfCat) {
        this.typeOfCat = typeOfCat;
    }

    private String typeOfCat;

    @Override
    public String toString() {
        return idOfCat + " ; " + typeOfCat + " near cat number : " + catDist.size() + "number of category's reciepe :" + recDist.size() + "\n";
    }
}

