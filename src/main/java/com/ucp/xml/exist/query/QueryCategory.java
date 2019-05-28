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

    public QueryCategory(){
        try {
            BasicConfigurator.configure();

            Class classDriver = forName(driver);
            Database database = (Database)classDriver.newInstance();

            DatabaseManager.registerDatabase(database);

            collection = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/bcd/","admin","bcd1234");

        }catch (Exception e){
            System.err.println("[ERROR] : "+e);
            collection = null;
        }
    }

    public List<String> findFriends(String idUser){
        List<String> friends = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query(" let $doc := //category[@type='plat']/recipes/recipe[@id_r='7'] return update value $doc/@dist_r with '5.0'");
            ResourceIterator i = result.getIterator();


            while(i.hasMoreResources()) {
                Resource r = i.nextResource();
                friends.add((String)r.getContent());
            }
        }catch (Exception e){
            System.err.println("[ERROR][Query findFriends] "+e);
        }
        return friends;
    }

    public void addCategory(Category category){
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            String query = "<category ";

            query = query + " id_c='"+category.getIdOfCat()+"' type='"+category.getTypeOfCat()+"'><near_categories>";

            for (Map.Entry<Integer,Float> e : category.getCatDist().entrySet()) {

                query = query + "<near_category id_c='" + e.getKey() + "' dist_c='" + e.getValue() + "'/>";
            }
            query = query+"</near_categories>";
            query = query+"<recipes>";
            for (Map.Entry<Integer,Float> e : category.getRecDist().entrySet()) {
                query = query + "<recipe id_r='" + e.getKey() + "' dist_r='" + e.getValue() + "'/>";
            }
            query = query+"</recipes>";
            query = query+"</category>";

            service.query("update insert "+query+" into //categories");

        }catch (Exception e){
            System.err.println("[ERROR][Query addCategories] "+e);
        }
    }

    public void addCategories(List<Category> categories){
        for (Category category : categories ){
            addCategory(category);
        }
    }


    public void printAllCat(){
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("//categories");
            ResourceIterator i = result.getIterator();
            while(i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println((String)r.getContent());
            }

        }catch (Exception e){
            System.err.println("[ERROR][Query addCategories] "+e);
        }
    }

    public void removeAll(){
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            /**/
            service.query("for $categories in //categories/category return update delete $categories");
        }catch (Exception e){
            System.err.println("[ERROR][Query removeAll] ");
            e.printStackTrace();
        }
    }

    public ArrayList<String> findRecipe(String id_r){
        ArrayList<String> recipeID = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            /**/
            ResourceSet result = service.query("for $category in //categories/category "+"where $category/recipes/recipe/@id_r='"+id_r+"'"+"return $category/recipes/recipe/@id_r/string()");
            ResourceIterator i = result.getIterator();
            while(i.hasMoreResources()) {
                Resource r = i.nextResource();
                recipeID.add((String)r.getContent());
            }

        }catch (Exception e){
            System.err.println("[ERROR][Query findRecipe with id_r ="+id_r+"] ");
            e.printStackTrace();
        }
        return recipeID;
    }

    public HashMap<Integer, String> findCategoriesByType(String type){
        HashMap<Integer, String> categories =  new HashMap<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("//categories/category[@type='"+type+"']/@id_c/string()");
            ResourceIterator i = result.getIterator();
            int index = 0;
            while(i.hasMoreResources()) {
                Resource r = i.nextResource();
                categories.put(index, (String)r.getContent());
                index++;
            }

        }catch (Exception e){
            System.err.println("[ERROR][Query findCategoriesByType with type ="+type+"] ");
            e.printStackTrace();
        }
        return categories;


    }
}
