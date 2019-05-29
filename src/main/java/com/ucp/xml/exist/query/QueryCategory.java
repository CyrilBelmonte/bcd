package com.ucp.xml.exist.query;

import com.ucp.xml.parse_xml.parser_csv.dao.category.Category;
import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Class.forName;

public class QueryCategory {
    private static String driver = "org.exist.xmldb.DatabaseImpl";
    private Collection collection;

    public QueryCategory() {
        try {
            BasicConfigurator.configure();

            Class classDriver = forName(driver);
            Database database = (Database) classDriver.newInstance();

            DatabaseManager.registerDatabase(database);

            collection = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/bcd/", "admin", "bcd1234");

        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : QueryCategory]");
            e.printStackTrace();
            collection = null;
        }
    }

    /*
     *Function to add 1 category
     */
    public void addCategory(Category category) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            String query = "<category ";

            query = query + " id_c='" + category.getIdOfCat() + "' type='" + category.getTypeOfCat() + "'><near_categories>";

            for (Map.Entry<Integer, Float> e : category.getCatDist().entrySet()) {

                query = query + "<near_category id_c='" + e.getKey() + "' dist_c='" + e.getValue() + "'/>";
            }
            query = query + "</near_categories>";
            query = query + "<recipes>";
            for (Map.Entry<Integer, Float> e : category.getRecDist().entrySet()) {
                query = query + "<recipe id_r='" + e.getKey() + "' dist_r='" + e.getValue() + "'/>";
            }
            query = query + "</recipes>";
            query = query + "</category>";

            service.query("update insert " + query + " into //categories");

        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : addCategory]");
            e.printStackTrace();
        }
    }

    /*
     *Function to add many category
     */
    public boolean addCategories(List<Category> categories) {
        try {
            for (Category category : categories) {
                addCategory(category);
            }
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : addCategories]");
            e.printStackTrace();
            return false;
        }
    }

    /*
     *Function to printAllCat in the eXist DB
     */
    public void printAllCat() {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("//categories");
            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String) r.getContent());
            }

        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : printAllCat]");
            e.printStackTrace();
        }
    }

    /*
     *Function to remove all categories
     */
    public void removeAll() {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            /**/
            service.query("for $categories in //categories/category return update delete $categories");
        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : removeAll]");
            e.printStackTrace();
        }
    }

    /*
     * Function to find all recipe in the recipe's category
     */
    public ArrayList<Integer> findRecipe(int id_r) {
        ArrayList<Integer> recipeID = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            /**/
            ResourceSet result = service.query("for $category in //categories/category " + "where $category/recipes/recipe/@id_r='" + id_r + "'" + "return $category/recipes/recipe/@id_r/string()");
            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                recipeID.add(Integer.parseInt((String) r.getContent()));
            }

        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : findRecipe] with arg type [" + id_r + "] ");
            e.printStackTrace();
        }
        return recipeID;
    }

    /*
     *Function to find the category's id with a recipe's id
     */
    public int findCatByRecipe(int idRecipe) {
        String results = "0";
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            /**/
            ResourceSet result = service.query("for $category in //categories/category where $category/recipes/recipe/@id_r='" + idRecipe + "'return $category/@id_c/string()");
            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                results = (String) r.getContent();
            }
        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : findCatByRecipe] with arg type [" + idRecipe + "] ");
            e.printStackTrace();
        }
        return Integer.parseInt(results);
    }

    /*
     * Function to return the list of categories' with a type
     */
    public HashMap<String, Float> findCategoriesByType(String type) {
        HashMap<String, Float> categories = new HashMap<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet resultId = service.query("//categories/category[@type='" + type + "']/@id_c/string()");
            ResourceIterator i = resultId.getIterator();
            int count = countCategoriesByType(type);
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                categories.put(r.getContent().toString(), 1f / count);
            }

        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : findCategoriesByType] with arg type [" + type + "] ");
            e.printStackTrace();
        }
        return categories;
    }

    /*
     *Function to count the categories' number by type
     */
    public Integer countCategoriesByType(String type) {
        Integer count = 0;

        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet resultId = service.query("count(//category[@type='" + type + "'])");
            ResourceIterator i = resultId.getIterator();

            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                count = Integer.parseInt(r.getContent().toString());
            }

        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : countCategoriesByType] with arg type [" + type + "] ");
            e.printStackTrace();
        }
        return count;
    }

    /*
     *Function to know the category's type
     */
    public String getTypeCat(int idCategory) {
        String results = "";
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("//categories/category[@id_c='" + idCategory + "']/@type/string()");
            ResourceIterator i = result.getIterator();

            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                results = results + r.getContent();
            }
        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : getTypeCat] with arg idCategory [" + idCategory + "] ");
            e.printStackTrace();
        }
        return results;
    }

    /*
     * Function to return recipes's list for a category
     */
    public ArrayList<Integer> getRecipeByCat(int idCategory){
        ArrayList<Integer> recipes = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("//categories/category[@id_c='" + idCategory + "']/recipes/recipe/@id_r/string()");
            ResourceIterator i = result.getIterator();

            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                recipes.add(Integer.parseInt ((String)r.getContent()));
            }
        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCategory] [method : getRecipeByCat] with arg idCategory [" + idCategory + "] ");
            e.printStackTrace();
        }
        return recipes;
    }
}
