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
    private static final int EPSILON = 4;

    public QuerySimpleUser() {
        try {
            BasicConfigurator.configure();

            Class classDriver = forName(driver);
            Database database = (Database) classDriver.newInstance();

            DatabaseManager.registerDatabase(database);

            collection = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/bcd/", "admin", "bcd1234");

        } catch (Exception e) {
            System.err.println("[ERROR][class : QuerySimpleUser] [method : QuerySimpleUser]");
            e.printStackTrace();
            collection = null;
        }
    }

    /*
     *Function to add friend for an user
     */
    public boolean addFriend(int idUser, int idFriend) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            String query = "<friend id_u='" + idFriend + "'/>";
            service.query("update insert " + query + "  into //users/user[@id_u='" + idUser + "']/friends");

            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][class : QuerySimpleUser] [method : addFriend]");
            e.printStackTrace();
            return false;
        }
    }

    /*
     *Function to add a bookmark for an user
     */
    public boolean addBookmark(int idUser, int idRecipe) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            String query = "<bookmark id_r='" + idRecipe + "'/>";
            service.query("update insert " + query + "  into //users/user[@id_u='" + idUser + "']/bookmarks");

            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][class : QuerySimpleUser] [method : addBookmark]");
            e.printStackTrace();
            return false;
        }
    }

    /*
     *Function to return the bookmark's list of one user
     */
    public List<Integer> bookmarksList(int idUser) {
        List<Integer> bookmarks = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            ResourceSet result = service.query("//users/user[@id_u='" + idUser + "']/bookmarks/bookmark/@id_r/string()");

            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                bookmarks.add(Integer.parseInt((String) r.getContent()));
            }
        } catch (Exception e) {
            System.err.println("[ERROR][class : QuerySimpleUser] [method : bookmarkList]");
            e.printStackTrace();
        }
        return bookmarks;
    }

    /*
     *Function to delete a bookmark of one user
     */
    public boolean deleteBookmark(int idUser, int idRecipe) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            service.query("for $bookmark in //users/user[@id_u='" + idUser + "']/bookmarks/bookmark[@id_r='" + idRecipe + "'] return update delete $bookmark");
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][class : QuerySimpleUser] [method : deleteBookmark]");
            e.printStackTrace();
            return false;
        }
    }

    /*
     *Function to return the friend's list of one user
     */
    public ArrayList<Integer> friendsList(int idUser) {
        ArrayList<Integer> friends = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            ResourceSet result = service.query("//users/user[@id_u='" + idUser + "']/friends/friend/@id_u/string()");

            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                friends.add(Integer.parseInt((String) r.getContent()));
            }
        } catch (Exception e) {
            System.err.println("[ERROR][class : QuerySimpleUser] [method : friendsList]");
            e.printStackTrace();
        }
        return friends;
    }

    /*
     * Function to delete a friend of one user
     */
    public boolean deleteFriend(int idUser, int idFriend) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            service.query("for $friends in //users/user[@id_u='" + idUser + "']/friends/friend[@id_u='" + idFriend + "'] return update delete $friends");
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][class : QuerySimpleUser] [method : deleteFriend]");
            e.printStackTrace();
            return false;
        }
    }

    /*
     *Function to update the category who the user put a mark
     */
    public boolean majCat(int idUser, int idRecipe, int mark) {

        QueryCategory queryCategory = new QueryCategory();
        int idCategory = queryCategory.findCatByRecipe(idRecipe);
        String typeCategory = queryCategory.getTypeCat(idCategory);

        HashMap<Integer, Float> tCategories = new HashMap<>();
        HashMap<Integer, Float> tP1Categories = new HashMap<>();

        float err = 1 / (6 - mark);
        System.out.println("Id user = " + idUser + " Id Category " + idCategory + " Type = " + typeCategory);


        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            ResourceSet result = service.query("for $category in //users/user[@id_u='" + idUser + "']/categories/type[@value='" + typeCategory + "']/category return  $category/@id_c/string() ||\";\"|| $category/@prob/string()");
            ResourceIterator i = result.getIterator();

            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                String tab[] = ((String) r.getContent()).split(";");
                tCategories.put(Integer.parseInt(tab[0]), Float.parseFloat(tab[1]));
            }
            Float d_tp1 = (tCategories.get(idCategory)) * EPSILON * err;

            tCategories.put(idCategory, d_tp1);

            Float sum = 0.0f;

            for (Map.Entry<Integer, Float> entry : tCategories.entrySet()) {
                sum = sum + entry.getValue();
            }
            for (Map.Entry<Integer, Float> entry : tCategories.entrySet()) {
                Float prob = entry.getValue();
                Integer id_c = entry.getKey();
                if (prob == 0.0) {
                    prob = 0.000000001f;
                }
                tP1Categories.put(id_c, prob / sum);
            }

            for (Map.Entry<Integer, Float> entry : tP1Categories.entrySet()) {
                service.query("let $doc := //users/user[@id_u='" + idUser + "']/categories/type[@value='" + typeCategory + "']/category[@id_c='" + entry.getKey() + "'] return update value $doc/@prob with '" + entry.getValue() + "'");
            }
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR][class : QuerySimpleUser] [method : majCat]");
            e.printStackTrace();
            return false;
        }
    }

    /*
     *Function to return a categories' list to suggest category
     */
    public ArrayList<Integer> getFirstCategory(int idUser, String type) {
        ArrayList<Integer> catList = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("let $path := //users/user[@id_u='" + idUser + "']/categories/type[@value='" + type + "']/category let $maxU := max($path/@prob) for $category in $path where $category/@prob = $maxU return $category/@id_c/string()");
            ResourceIterator i = result.getIterator();


            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                catList.add(Integer.parseInt((String) r.getContent()));
            }
        } catch (Exception e) {
            System.err.println("[ERROR][class : QuerySimpleUser] [method : getFirstCategory]");
            e.printStackTrace();
        }
        return catList;
    }

    /*
     *Function to return recipe
     */
    public ArrayList<Integer> getNearRecipe(int idUser, String type, int nbResult) {
        ArrayList<Integer> recipe = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("(let $cats := //users/user[@id_u='" + idUser + "']/categories/type[@value='" + type + "']/category " +
                    "for $cat in $cats " +
                    "order by $cat/@prob " +
                    "for $recipe in //categories/category[@id_c = $cat/@id_c]/recipes/recipe " +
                    "order by $recipe/@dist_r " +
                    "return $recipe/@id_r/string())[position() = 0 to " + nbResult + "]");
            ResourceIterator i = result.getIterator();


            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                recipe.add(Integer.parseInt((String) r.getContent()));
            }
        } catch (Exception e) {
            System.err.println("[ERROR][class : QuerySimpleUser] [method : getNearRecipe]");
            e.printStackTrace();
        }
        return recipe;

    }

    public ArrayList<Integer> getUserList() {
        ArrayList<Integer> userList = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("//users/user/@id_u/string()");
            ResourceIterator i = result.getIterator();


            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                userList.add(Integer.parseInt((String) r.getContent()));
            }
        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryUserList] [method : getUserList]");
            e.printStackTrace();
        }
        return userList;
    }

    public ArrayList<Double> getAllProbByCatByType(int idUser, String type) {
        ArrayList<Double> catList = new ArrayList<>();
        try {

            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("//users/user[@id_u='" + idUser + "']/categories/type[@value='" + type + "']/category/@prob/string()");
            ResourceIterator i = result.getIterator();


            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                catList.add(Double.parseDouble((String) r.getContent()));
            }
        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryCatListByUser] [method : getAllCatByType]");
            e.printStackTrace();
        }
        return catList;
    }

    public ArrayList<Integer> findAllCatByOrder(int idUser, String type) {
        ArrayList<Integer> catList = new ArrayList<>();
        try {

            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("for $category in //users/user[@id_u='" + idUser + "']/categories/type[@value='" + type + "']/category order by $category/@prob descending return $category/@id_c/string()");
            ResourceIterator i = result.getIterator();


            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                catList.add(Integer.parseInt((String) r.getContent()));
            }
        } catch (Exception e) {
            System.err.println("[ERROR][class : QueryFindAllCatByOrder] [method : findAllCatByOrder]");
            e.printStackTrace();
        }
        return catList;
    }

}


