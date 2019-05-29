package com.ucp.xml.exist.query;

import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Class.forName;

public class QuerySimpleUser {
    private static String driver = "org.exist.xmldb.DatabaseImpl";
    private Collection collection;
    private static final int EPSILON =4;

    public QuerySimpleUser() {
        try {
            BasicConfigurator.configure();

            Class classDriver = forName(driver);
            Database database = (Database) classDriver.newInstance();

            DatabaseManager.registerDatabase(database);

            collection = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/bcd/", "admin", "bcd1234");

        } catch (Exception e) {
            System.err.println("[ERROR QueryUser] : " + e);
            collection = null;
        }
    }

    public boolean addFriend(String idUser, String idFriend) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            String query = "<friend id_u='" + idFriend + "'/>";
            service.query("update insert " + query + "  into //users/user[@id_u='" + idUser + "']/friends");

            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][Query addFriend] " + e);
            return false;
        }
    }

    public boolean addBookmark(String idUser, String idRecipe) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            String query = "<bookmark id_r='" + idRecipe + "'/>";
            service.query("update insert " + query + "  into //users/user[@id_u='" + idUser + "']/bookmarks");

            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][Query addBookmark] " + e);
            return false;
        }
    }

    public List<String> bookmarksList(String idUser) {
        List<String> bookmarks = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            ResourceSet result = service.query("//users/user[@id_u='" + idUser + "']/bookmarks");

            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                bookmarks.add((String) r.getContent());
            }
        } catch (Exception e) {
            System.err.println("[ERROR][Query addBookmark] " + e);
        }
        return bookmarks;
    }

    public boolean deleteBookmark(String idUser, String idRecipe) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            service.query("for $bookmark in //users/user[@id_u='" + idUser + "']/bookmarks/bookmark[@id_r='" + idRecipe + "'] return update delete $bookmark");
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][Query addBookmark] " + e);
            return false;
        }
    }

    public ArrayList<String> friendsList(String idUser) {
        ArrayList<String> friends = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            ResourceSet result = service.query("//users/user[@id_u='" + idUser + "']/friends/friend/@id_u");

            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                friends.add((String) r.getContent());
            }
        } catch (Exception e) {
            System.err.println("[ERROR][Query friendsList] " + e);
        }
        return friends;
    }

    public boolean deleteFriend(String idUser, String idFriend) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            service.query("for $friends in //users/user[@id_u='" + idUser + "']/friends/friend[@id_u='" + idFriend + "'] return update delete $friends");
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][Query addBookmark] " + e);
            return false;
        }
    }

    public boolean majCat(String idUser, String idRecipe, int mark) {

        QueryCategory queryCategory = new QueryCategory();
        String idCategory = queryCategory.findCatByRecipe(idRecipe);
        String typeCategory = queryCategory.getTypeCat(idCategory);

        HashMap<String , Float> tCategories = new HashMap<>();
        HashMap<String ,Float> tP1Categories = new HashMap<>();

        int err = 1/(6-mark);
        System.out.println("Id user = "+idUser+" Id Category "+idCategory+" Type = "+typeCategory);


        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            ResourceSet result = service.query("for $category in //users/user[@id_u='"+idUser+"']/categories/type[@value='"+typeCategory+"']/category return  $category/@id_c/string()||';'||$category/@proba/string()");
            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                String tab[] = ((String) r.getContent()).split(";");
                tCategories.put(tab[0],Float.parseFloat(tab[1]));
            }

            Float d_tp1 = (tCategories.get(idCategory))*EPSILON*err;

            tCategories.put(idCategory,d_tp1);

            Float sum = 0.0f;

            for(Map.Entry<String, Float> entry : tCategories.entrySet()) {
                sum = sum + entry.getValue();
            }

            System.out.println("SOMME = "+sum);
            for(Map.Entry<String, Float> entry : tCategories.entrySet()) {
                Float prob = entry.getValue();
                String id_c = entry.getKey();

                tP1Categories.put(id_c,prob/sum);
            }

            for(Map.Entry<String, Float> entry : tP1Categories.entrySet()) {
                service.query("let $doc := //users/user/categories/type[@value='"+typeCategory+"']/category[@id_c='"+entry.getKey()+"'] return update value $doc/@proba with '"+entry.getValue()+"'");
            }
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][Query majCat] " + e);
            return false;
        }
    }
}
