package com.ucp.xml.parse_xml.parser_csv.dao.test;

import com.ucp.xml.exist.query.QueryCategory;

import com.ucp.xml.parse_xml.parser_csv.dao.category.Category;
import com.ucp.xml.parse_xml.parser_csv.dao.category.CategorieDao;
import com.ucp.xml.parse_xml.parser_csv.dao.category.CsvCatergoryDao;
import com.ucp.xml.parse_xml.parser_csv.dao.helper.CsvFileHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test {
    private static final String FILE_NAME ="src/main/resources/SortiIA.csv";
    private static File file;
    private static CategorieDao categorieDao;

    public static void main(String[] args) {

        file = CsvFileHelper.getRessource(FILE_NAME);
        categorieDao = new CsvCatergoryDao(file);
        List<Category> categoriesList = new ArrayList<>();

        categoriesList = categorieDao.findAllCategorie();

        QueryCategory queryCategory = new QueryCategory();

        System.out.println("ERASE");
        queryCategory.removeAll();
        System.out.println("PRINT");
        queryCategory.printAllCat();
        System.out.println("ADD");
        queryCategory.addCategories(categoriesList);

        System.out.println("PRINT");
        queryCategory.printAllCat();
        System.out.println(categoriesList.get(0).getIdOfCat());
        Map<String, Float> test = queryCategory.findCategoriesByType("dessert");
        System.out.println(test.toString());
    }
}
