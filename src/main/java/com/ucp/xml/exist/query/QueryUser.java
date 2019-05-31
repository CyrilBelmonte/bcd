package com.ucp.xml.exist.query;

import com.ucp.xml.parse_xml.user_xml.dao.user.SimpleUser;
import com.ucp.xml.parse_xml.user_xml.dao.user.User;
import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Class.forName;

public class QueryUser {
    private static String driver = "org.exist.xmldb.DatabaseImpl";
    private Collection collection;

    public QueryUser(){
        try {
                InitConnection connection = new InitConnection();
                collection = connection.getCollection();
        }catch (Exception e){
            System.err.println("[ERROR] : "+e);
            collection = null;
        }
    }

    public void addUser(User user) {

        int starterCount = user.getEntreeCategories().size();
        int mainCount = user.getPlatCategories().size();
        int dessertCount = user.getDessertCategories().size();

        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            String query = "<user id_u='" + user.getIdUser() + "'>";

            query += "<categories><type value='starter' sum='" + starterCount + "'>";

            for (Map.Entry<Integer, Float> entry: user.getEntreeCategories().entrySet()) {
                query += "<category id_c='" + entry.getKey() + "' proba='" + 1f/starterCount + "'/>";
            }

            query += "</type><type value='main_Courses' sum='" + mainCount + "'>";

            for (Map.Entry<Integer, Float> entry : user.getPlatCategories().entrySet()) {
                query += "<category id_c='" + entry.getKey() + "' proba='" + 1f/mainCount + "'/>";
            }

            query += "</type><type value='dessert' sum='" + dessertCount + "'>";

            for (Map.Entry<Integer, Float> entry : user.getDessertCategories().entrySet()) {
                query += "<category id_c='" + entry.getKey() + "' proba='" + 1f/dessertCount + "'/>";
            }

            query += "</type></categories><friends>";

            for (Map.Entry<Integer, String> entryFriend : user.getFriends().entrySet()) {
                query += "<user id_ref='" + entryFriend.getValue() + "'/>";
            }

            query += "</friends><bookmarks>";

            for (Map.Entry<Integer, String> entryBookmarks : user.getBookmarks().entrySet()) {
                query += "<recipe id_r='" + entryBookmarks.getValue() + "'/>";
            }

            query += "</bookmarks></user>";

            service.query("update insert " + query + "into //users");

        } catch (Exception e) {
            System.err.println("[ERROR] [Query addUsers] " + e);
        }
    }

    public void addSimpleUser(Integer idUser) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            String query = "<user id_u='" + idUser + "'>";
            query += "<categories><type value='starter'>";
            query += "<category id_c='0' proba='0.5' />";
            query += "<category id_c='1' proba='0.5' />";

            query += "</type><type value='main_Courses'>";
            query += "<category id_c='2' proba='0.5' />";
            query += "<category id_c='3' proba='0.5' />";

            query += "</type><type value='dessert'>";
            query += "<category id_c='4' proba='0.5' />";
            query += "<category id_c='5' proba='0.5' />";

            query += "</type></categories><friends>";
            query += "</friends><bookmarks>";
            query += "</bookmarks></user>";

            service.query("update insert " + query + "into //users");

        } catch (Exception e) {
            System.err.println("[ERROR] [Query addSimpleUser] " + e);
        }
    }

    public void addUsers(List<User> users) {
        for (User user : users) {
            addUser(user);
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("/users/user/@id_u/string()");
            ResourceIterator i = result.getIterator();

            while(i.hasMoreResources()) {
                Resource r = i.nextResource();

                // Create User and add him
                User user = new SimpleUser(Integer.parseInt(r.getContent().toString()));
                user.setEntreeCategories(getCategories(Integer.parseInt(r.getContent().toString()), "starter"));
                user.setPlatCategories(getCategories(Integer.parseInt(r.getContent().toString()), "main_Courses"));
                user.setDessertCategories(getCategories(Integer.parseInt(r.getContent().toString()), "dessert"));
                users.add(user);
            }
        } catch (Exception e) {
            System.err.println("[ERROR] [Query getUsers] "+e);
            e.printStackTrace();
        }
        return users;
    }

    public List<User> getAllCompleteUsers() {
        List<User> users = new ArrayList<>();
        int threshold = 2; // Nombre de catégorie limite pour être dans un profil

        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("for $users in /users/user\n" +
                    "let $starter_cat := $users/categories/type[@value='starter']/category\n" +
                    "let $main_Courses_cat := $users/categories/type[@value='main_Courses']/category\n" +
                    "let $dessert_cat := $users/categories/type[@value='dessert']/category\n" +
                    "where count($starter_cat) > " + threshold +
                    "and count($main_Courses_cat) > " + threshold +
                    "and count($dessert_cat) > " + threshold +
                    "return $users/@id_u/string()");
            ResourceIterator i = result.getIterator();

            while(i.hasMoreResources()) {
                Resource r = i.nextResource();

                // Create User and add him
                User user = new SimpleUser(Integer.parseInt(r.getContent().toString()));
                user.setEntreeCategories(getCategories(Integer.parseInt(r.getContent().toString()), "starter"));
                user.setPlatCategories(getCategories(Integer.parseInt(r.getContent().toString()), "main_Courses"));
                user.setDessertCategories(getCategories(Integer.parseInt(r.getContent().toString()), "dessert"));
                users.add(user);
                System.out.println("User " + user.toString());
            }
        } catch (Exception e) {
            System.err.println("[ERROR] [Query getAllCompleteUsers] " + e);
        }
        return users;
    }

    public void setSimpleUserStaters(Integer idUser, HashMap<String, Float> categories){

    }
    public void setSimpleUserMainCourses(Integer idUser, HashMap<String, Float> categories){

    }
    public void setSimpleUserDesserts(Integer idUser, HashMap<String, Float> categories){

    }
    public void printAllUser() {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("//users");
            ResourceIterator i = result.getIterator();
            while(i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println(r.getContent().toString());
            }

        }catch (Exception e){
            System.err.println("[ERROR][Query addUsers] "+e);
        }
    }

    public void removeAll(){
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            /**/
            service.query("for $users in //users/user return update delete $users");
        }catch (Exception e){
            System.err.println("[ERROR][Query removeAll] ");
            e.printStackTrace();
        }
    }

    public HashMap<Integer, Float> getCategories(Integer idUser, String type){
        HashMap<Integer, Float> categories =  new HashMap<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("for $category in //users/user[@id_u='" + idUser + "']/categories/type[@value='" + type + "']/category return  $category/@id_c/string()||';'||$category/@proba/string()");
            ResourceIterator i = result.getIterator();
            while(i.hasMoreResources()) {
                Resource r = i.nextResource();
                String tab[] = ((String) r.getContent()).split(";");
                categories.put(Integer.parseInt(tab[0]), Float.parseFloat(tab[1]));
            }

        } catch (Exception e) {
            System.err.println("[ERROR][Query findCategoriesByType with type =" + type + "] ");
            e.printStackTrace();
        }
        return categories;
    }
}
