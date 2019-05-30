package com.ucp.xml.parse_xml.parser_csv.dao;

import com.ucp.xml.parse_xml.parser_csv.dao.category.CategorieDao;
import com.ucp.xml.parse_xml.parser_csv.dao.category.Category;
import com.ucp.xml.parse_xml.parser_csv.dao.category.CsvCatergoryDao;
import com.ucp.xml.parse_xml.parser_csv.dao.helper.CsvFileHelper;

import java.io.File;
import java.util.List;
import java.util.Map;

public class CSVToExistDB {

    public static void main(String[] args) {
        String FILE_NAME = "src/main/resources/SortiIA.csv";

        File file;
        CategorieDao categorieDao;
        file = CsvFileHelper.getRessource(FILE_NAME);
        categorieDao = new CsvCatergoryDao(file);

        List<Category> categoriesList;

        categoriesList = categorieDao.findAllCategorie();
        System.out.println("--------------- START ---------------");
/*
        QueryCategory queryCategory = new QueryCategory();
        System.out.println("--------------- ERASE ---------------");
        queryCategory.removeAll();
        System.out.println("Verification on Exist DB");
        queryCategory.printAllCat();

        System.out.println("--------- ADDING CATEGORIES ----------");
        System.out.println(categoriesList.size() + " will been added");
        boolean res = queryCategory.addCategories(categoriesList);
        if (res) {
            System.out.println(categoriesList.size() + " have been added");
        }*/
        int recipeSum = 0;
        System.out.println("--> Category : " + "Starter");
        for (int index = 0; index < categoriesList.size(); index++) {
            if (categoriesList.get(index).getTypeOfCat().equals("starter")) {
                System.out.println("----> Category id: " + categoriesList.get(index).getIdOfCat() + " type : " + categoriesList.get(index).getTypeOfCat() + " Nb recipe :" + categoriesList.get(index).getRecDist().size());
                for (Map.Entry<Integer, Float> e : categoriesList.get(index).getRecDist().entrySet()) {
                    System.out.println("\t|---------> id_r :" + e.getKey() + " dist :" + e.getValue());
                    recipeSum++;
                }
            }
        }
        System.out.println("--> Category : " + "Main Courses");
        for (int index = 0; index < categoriesList.size(); index++) {
            if (categoriesList.get(index).getTypeOfCat().equals("main_Courses")) {
                System.out.println("----> Category id: " + categoriesList.get(index).getIdOfCat() + " type : " + categoriesList.get(index).getTypeOfCat() + " Nb recipe :" + categoriesList.get(index).getRecDist().size());
                for (Map.Entry<Integer, Float> e : categoriesList.get(index).getRecDist().entrySet()) {
                    System.out.println("\t|---------> id_r :" + e.getKey() + " dist :" + e.getValue());
                    recipeSum++;
                }
            }
        }
        System.out.println("--> Category : " + "Dessert");
        for (int index = 0; index < categoriesList.size(); index++) {
            if (categoriesList.get(index).getTypeOfCat().equals("dessert")) {
                System.out.println("----> Category id: " + categoriesList.get(index).getIdOfCat() + " type : " + categoriesList.get(index).getTypeOfCat() + " Nb recipe :" + categoriesList.get(index).getRecDist().size());
                for (Map.Entry<Integer, Float> e : categoriesList.get(index).getRecDist().entrySet()) {
                    System.out.println("\t|---------> id_r :" + e.getKey() + " dist :" + e.getValue());
                    recipeSum++;
                }
            }
        }
        System.out.println("Number of categories : " + categoriesList.size() + " nb recipes : " + recipeSum);
        System.out.println("---------------- END ----------------");
    }
}
