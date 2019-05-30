package com.ucp.xml.exist.query;

import com.ucp.cookwithease.model.Recipe;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.Profile;
import com.ucp.xml.parse_xml.user_xml.dao.user.User;
import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.util.List;
import java.util.Map;

import static java.lang.Class.forName;

public class QueryProfile {
    private static String driver = "org.exist.xmldb.DatabaseImpl";
    private Collection collection;

    public QueryProfile(){
        try {
            BasicConfigurator.configure();

            Class classDriver = forName(driver);
            Database database = (Database) classDriver.newInstance();

            DatabaseManager.registerDatabase(database);

            collection = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/bcd/", "admin", "bcd1234");
        }catch (Exception e){
            System.err.println("[ERROR] : "+e);
            collection = null;
        }
    }

    public void addProfile(Profile profile) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            String query = "<profile id='" + profile.getIdProfile() + "'>";

            for (User profileUser : profile.getUsers()) {
                query += "<users id='"+ profileUser.getIdUser() +"'/>";
            }

            query += "</profile>";

            service.query("update insert " + query + "into //profiles");
        } catch (Exception e) {
            System.err.println("[Error] [Query addProfile] " + e);
            e.printStackTrace();
        }
    }

    public void addProfiles(List<Profile> profiles) {
        for (Profile profile : profiles) {
            addProfile(profile);
        }
    }
    public void printAllProfile() {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            ResourceSet result = service.query("//profiles");
            ResourceIterator i = result.getIterator();
            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                System.out.println(r.getContent().toString());
            }

        } catch (Exception e) {
            System.err.println("[ERROR][Query printProfiles] " + e);
        }
    }

    public void removeAll() {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            service.query("for $profiles in //profiles/profile return update delete $profiles");
        } catch (Exception e) {
            System.err.println("[ERROR][Query removeAll] ");
            e.printStackTrace();
        }
    }

    public List<Profile> getProfile(int idUser) {
        return null;
    }

    public boolean updateProfile() {
        return false;
    }

    public List<Recipe> suggestRecipesByProfile(int idUser, String type) {
        return null;
    }
}
