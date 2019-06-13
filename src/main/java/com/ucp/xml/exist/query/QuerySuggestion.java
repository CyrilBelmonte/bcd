package com.ucp.xml.exist.query;

import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;

import java.util.HashMap;
import java.util.List;

import static java.lang.Class.forName;

public class QuerySuggestion {
    private static String driver = "org.exist.xmldb.DatabaseImpl";
    private Collection collection;

    public QuerySuggestion() {
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

    public HashMap<Integer, List<Integer>> suggestion(int idUser, String type) {
        HashMap<Integer, List<Integer>> listCatWithRecipe = new HashMap<>();






        return listCatWithRecipe;
    }
}
