package com.ucp.xml.exist.query;

import com.ucp.cookwithease.model.Recipe;
import com.ucp.xml.parse_xml.user_xml.dao.user.SimpleUser;
import com.ucp.xml.parse_xml.user_xml.dao.user.User;
import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.util.ArrayList;
import java.util.List;

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

    public void addProfile(ArrayList<Integer> profile, int index) {
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");

            String query = "<profile id_p='" + index + "'>";

            for (Integer idUser : profile) {
                query += "<user id_u='"+ idUser +"'/>";
            }

            query += "</profile>";

            service.query("update insert " + query + "into //profiles");
        } catch (Exception e) {
            System.err.println("[Error] [Query addProfile] " + e);
            e.printStackTrace();
        }
    }

    public void addProfiles(ArrayList<ArrayList<Integer>> profiles) {
        int index;
        for (index = 0; index < profiles.size(); index++) {
            addProfile(profiles.get(index), index);
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

    public Integer getIdProfileByIdUser(int idUser) {
        Integer idProfile = 0;

        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            ResourceSet result = service.query("/profiles/profile[user/@id_u='"+idUser+"']/@id_p/string()");
            ResourceIterator i = result.getIterator();

            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                idProfile = Integer.parseInt(r.getContent().toString());

            }
        } catch (Exception e) {
            System.err.println("[ERROR] [class: Query Profile] [method: suggestProfileByUser]");
            e.printStackTrace();
        }
        return idProfile;
    }

    public List<Integer> getIdUsersByIdProfile(int idProfile) {
        List<Integer> isUsers = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            ResourceSet result = service.query("/profiles/profile[@id_p='"+idProfile+"']/user/@id_u/string()");
            ResourceIterator i = result.getIterator();

            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                isUsers.add(Integer.parseInt(r.getContent().toString()));

            }
        } catch (Exception e) {
            System.err.println("[ERROR] [class: Query Profile] [method: suggestProfileByUser]");
            e.printStackTrace();
        }
        return isUsers;
    }

    public List<Integer> getIdUsersByIdUser(int idUser) {
        List<Integer> isUsers = new ArrayList<>();
        try {
            XPathQueryService service = (XPathQueryService) collection.getService("XPathQueryService", "1.0");
            service.setProperty("indent", "yes");
            ResourceSet result = service.query("/profiles/profile[user/@id_u='"+idUser+"']/user/@id_u/string()");
            ResourceIterator i = result.getIterator();

            while (i.hasMoreResources()) {
                Resource r = i.nextResource();
                isUsers.add(Integer.parseInt(r.getContent().toString()));

            }
        } catch (Exception e) {
            System.err.println("[ERROR] [class: Query Profile] [method: suggestProfileByUser]");
            e.printStackTrace();
        }
        return isUsers;
    }
}
