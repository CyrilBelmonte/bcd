package com.ucp.xml.parse_xml.parser_csv.dao.test;

import com.ucp.xml.parse_xml.parser_csv.csvtoxml.CsvXML;
import com.ucp.xml.parse_xml.parser_csv.dao.category.Category;
import com.ucp.xml.parse_xml.parser_csv.dao.category.CategorieDao;
import com.ucp.xml.parse_xml.parser_csv.dao.category.CsvCatergoryDao;
import com.ucp.xml.parse_xml.parser_csv.dao.helper.CsvFileHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Test {
    private static final String FILE_NAME ="src/main/resources/test.csv";
    private static File file;
    private static CategorieDao categorieDao;

    public static void main(String[] args) {
        file = CsvFileHelper.getRessource(FILE_NAME);
        categorieDao = new CsvCatergoryDao(file);
        List<Category> categoriesList = new ArrayList<>();

        categoriesList = categorieDao.findAllCategorie();

        for(int index = 0 ; index<categoriesList.size();index++){
            System.out.println("Index = "+index+" Category id : "+categoriesList.get(index).getIdOfCat()+" type : "+categoriesList.get(index).getTypeOfCat()+" cat voisine : "+categoriesList.get(index).getCatDist()+" reciepe(s) : "+categoriesList.get(index).getRecDist()+"\n");
        }

        CsvXML csvXML = new CsvXML(categoriesList);
        csvXML.creatDocumentXML();
    }
}
