package com.ucp.xml.exist.query;

import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;

import static java.lang.Class.forName;

public class InitConnection {
    public Collection collection;

    public InitConnection() {
        String driver = "org.exist.xmldb.DatabaseImpl";
        try {
            BasicConfigurator.configure();

            Class classDriver = forName(driver);
            Database database = (Database) classDriver.newInstance();

            DatabaseManager.registerDatabase(database);

            collection = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/bcd", "admin", "bcd1234");

        } catch (Exception e) {
            System.err.println("[ERROR] [class : InitConnection] [method : InitConnection] " + e);
            collection = null;
        }
    }

    public Collection getCollection() {
        return collection;
    }
}
