package com.ucp.xml.exist.query;

import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.modules.XPathQueryService;

import static java.lang.Class.forName;

public class InitConnection {
    private static String driver = "org.exist.xmldb.DatabaseImpl";
    public Collection collection;

    public InitConnection(){
        try {
            BasicConfigurator.configure();

            Class classDriver = forName(driver);
            Database database = (Database)classDriver.newInstance();

            DatabaseManager.registerDatabase(database);

             collection = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/bcd" ,"admin","bcd1234");
            System.out.println("InitConnectionTest");


        }catch (Exception e){
            System.err.println("[ERROR] : "+e);
            collection = null;
        }
    }

    public Collection getCollection() {
        return collection;
    }
}
