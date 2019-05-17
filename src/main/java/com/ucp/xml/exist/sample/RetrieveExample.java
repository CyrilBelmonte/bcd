package com.ucp.xml.exist.sample;

import org.apache.log4j.BasicConfigurator;
import org.exist.xmldb.EXistResource;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;


public class RetrieveExample {
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/categories";

    private static String ressourceName="categories.xml";

    public static void main(String[] args) throws Exception{

        final String driver = "org.exist.xmldb.DatabaseImpl";

        BasicConfigurator.configure();

        Class cl = Class.forName(driver);

        Database database = (Database) cl.newInstance();
        database.setProperty("create-database","true");

        DatabaseManager.registerDatabase(database);

        Collection col = null;
        XMLResource res = null;

        try {
            col = DatabaseManager.getCollection(URI);
            //col.setProperty(OutputKeys.INDENT,"no");
            res = (XMLResource)col.getResource(ressourceName);


            if(res==null){
                System.err.println("document not found");
            }else {
                System.out.println(res.getContent());
            }
        } finally {
            if(res !=null){
                try{
                    ((EXistResource)res).freeResources();
                }catch (XMLDBException xe) {
                    System.err.println(xe.toString());
                }
            }

            if (col !=null ){
                try {
                    col.close();
                }catch (XMLDBException xe ){
                    System.err.println(xe.toString());
                }
            }
        }
    }
}
