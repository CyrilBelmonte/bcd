package com.ucp.xml.profiles;

import com.ucp.xml.exist.query.QuerySimpleUser;

import java.util.ArrayList;

public class User4Prof {

    private ArrayList<Double> catMainCourse = new ArrayList<>();
    private ArrayList<Double> catStarter = new ArrayList<>();
    private ArrayList<Double> catDessert = new ArrayList<>();

    private QuerySimpleUser querySimpleUser = new QuerySimpleUser();

    private int user;

    public User4Prof(int user) {
        this.user = user;
        generateStarter();
        generateDessert();
        generateMainCourse();
    }

    public void generateStarter() {
        ArrayList<Double> catArrayStarter = querySimpleUser.getAllProbByCatByType(this.user, "starter");
        for (Double prob : catArrayStarter) {
            catStarter.add(prob);
        }
    }

    public void generateDessert() {
        ArrayList<Double> catArrayDessert = querySimpleUser.getAllProbByCatByType(this.user, "dessert");
        for (Double prob : catArrayDessert) {
            catDessert.add(prob);
        }
    }

    public void generateMainCourse() {
        ArrayList<Double> catArrayMC = querySimpleUser.getAllProbByCatByType(this.user, "main_course");
        for (Double prob : catArrayMC) {
            catMainCourse.add(prob);
        }
    }

    public int getUser() {
        return user;
    }

    public ArrayList<Double> getCatMainCourse() {
        return catMainCourse;
    }

    public ArrayList<Double> getCatStarter() {
        return catStarter;
    }

    public ArrayList<Double> getCatDessert() {
        return catDessert;
    }
}
