package com.ucp.xml.parse_xml.parser_csv.dao.category;


import com.ucp.xml.parse_xml.parser_csv.dao.csv.CsvFile;
import com.ucp.xml.parse_xml.parser_csv.dao.csv.CsvFileInt;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CsvCategoryDao implements CategoryDao {

    private File file;
    private CsvFileInt csvFileInt;
    private static final int NB_CAT = 199;

    @Override
    public List<Category> findAllCategory() {
        final List<Category> categories = new ArrayList<Category>();
        final List<String[]> data = csvFileInt.getData();
        final HashMap<String, Integer> countCategories = countCategories(data);
        for (String[] oneData : data) {
            Category category = tabToCategorie(oneData, countCategories);
            categories.add(category);
        }
        return categories;
    }

    public HashMap<String, Integer> countCategories(List<String[]> data) {
        HashMap<String, Integer> counts = new HashMap<>();

        counts.put("starter", NB_CAT);
        counts.put("main_course", NB_CAT);
        counts.put("dessert", NB_CAT);

        return counts;
    }

    private Category tabToCategorie(String[] tab, HashMap<String, Integer> countCategories) {
        SimpleCategory category = new SimpleCategory();


        HashMap<Integer, Float> catDist = new HashMap<Integer, Float>();
        HashMap<Integer, Float> recDist = new HashMap<Integer, Float>();

        String str = "";
        category.setIdOfCat(tab[0]);
        category.setTypeOfCat(tab[1]);

        int nb_categories = countCategories.get(tab[1]);

        int index_post_cat = nb_categories + 2 + 1;

        for (int index = 2; index < nb_categories + 2; index++) {
            catDist.put(index - 2, Float.valueOf(tab[index].trim()).floatValue());
        }
        try {
            while (index_post_cat < tab.length) {
                recDist.put(Integer.parseInt(tab[index_post_cat]), Float.valueOf(tab[index_post_cat + 1].trim()).floatValue());
                index_post_cat = index_post_cat + 2;
            }
        } catch (Exception e) {
            System.err.println("[ERROR][tabToCategory] Value = " + tab[index_post_cat] + " index " + index_post_cat);
        }

        category.setCatDist(catDist);
        category.setRecDist(recDist);

        return category;
    }

    private CsvCategoryDao() {
        super();
    }

    public CsvCategoryDao(File file) {
        this();
        this.file = file;
        this.csvFileInt = new CsvFile(file);

    }
}
