package com.ucp.xml.exist.query;

import com.ucp.xml.parse_xml.user_xml.dao.user.User;
import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.util.List;
import java.util.Map;

import static java.lang.Class.forName;

public class QueryUser {
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

            for (Map.Entry<Integer, String> entry: user.getEntreeCategories().entrySet()) {
                query += "<category id_c='" + entry.getValue() + "' proba='" + 1f/starterCount + "'/>";
            }

            query += "</type><type value='mainCourse' sum='" + 1f/mainCount + "'>";

            for (Map.Entry<Integer, String> entry : user.getPlatCategories().entrySet()) {
                query += "<category id_c='" + entry.getValue() + "' proba='" + 1f/mainCount + "'/>";
            }

            query += "</type><type value='dessert' sum='" + 1f/dessertCount + "'>";

            for (Map.Entry<Integer, String> entry : user.getDessertCategories().entrySet()) {
                query += "<category id_c='" + entry.getValue() + "' proba='" + 1f/dessertCount + "'/>";
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

    public void addUsers(List<User> users) {
        for (User user : users) {
            addUser(user);
        }
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
}
