package com.ucp.xml.parse_xml.parser_csv.dao.category;



import com.ucp.xml.parse_xml.parser_csv.dao.csv.CsvFile;
import com.ucp.xml.parse_xml.parser_csv.dao.csv.CsvFileInt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CsvCatergoryDao implements CategorieDao {

    private File file;
    private CsvFileInt csvFileInt;
    private static final int NBR_CATEGORIE = 4;

    @Override
    public List<Category> findAllCategorie(){
        final List<Category> categories = new ArrayList<Category>();
        final List<String []> data = csvFileInt.getData();
        for (String[] oneData : data){
            Category category = tabToCategorie(oneData);
            categories.add(category);
        }
        return categories;
    }

    private Category tabToCategorie(String [] tab){
        SimpleCategory categorie = new SimpleCategory();

        int index_post_cat = NBR_CATEGORIE+2+1;

        HashMap<Integer,Float> catDist = new HashMap<Integer, Float>() ;
        HashMap<Integer,Float> recDist = new HashMap<Integer, Float>();

        String str ="" ;
        categorie.setIdOfCat(tab[0]);
        categorie.setTypeOfCat(tab[1]);

        for (int index = 2 ; index < NBR_CATEGORIE+2 ;index++){
            catDist.put(index-2,Float.valueOf(tab[index].trim()).floatValue());
        }
        while(index_post_cat<tab.length){
            recDist.put(Integer.parseInt(tab[index_post_cat]),Float.valueOf(tab[index_post_cat+1].trim()).floatValue());
            index_post_cat= index_post_cat+2;
        }
        categorie.setCatDist(catDist);
        categorie.setRecDist(recDist);

        return categorie;
    }

    private CsvCatergoryDao(){
        super();
    }

    public CsvCatergoryDao(File file){
        this();
        this.file = file;
        this.csvFileInt = new CsvFile(file);

    }
}
