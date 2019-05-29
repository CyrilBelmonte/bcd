package com.ucp.xml.parse_xml.parser_csv.dao;

import com.ucp.xml.exist.query.QueryCategory;
import com.ucp.xml.parse_xml.parser_csv.dao.category.CategorieDao;
import com.ucp.xml.parse_xml.parser_csv.dao.category.Category;
import com.ucp.xml.parse_xml.parser_csv.dao.category.CsvCatergoryDao;
import com.ucp.xml.parse_xml.parser_csv.dao.helper.CsvFileHelper;

import java.io.File;
import java.util.List;

public class CSVToExistDB {

    public static void main(String[] args) {
        String FILE_NAME = "src/main/resources/SortiIA.csv";
        File file;
        CategorieDao categorieDao;
        file = CsvFileHelper.getRessource(FILE_NAME);
        categorieDao = new CsvCatergoryDao(file);

        List<Category> categoriesList;

        categoriesList = categorieDao.findAllCategorie();

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
        }
        System.out.println("---------------- END ----------------");
    }
}
