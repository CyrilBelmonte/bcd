package com.ucp.xml.exist.conf;

import org.exist.util.FileUtils;
import org.exist.xmldb.XmldbURI;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;


import java.nio.file.Path;
import java.nio.file.Paths;


public class CollectionDB {

    String driver = "org.exist.xmldb.DatabaseImpl";

    public void addCollectionDB(String collection,String URI) throws Exception {

        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        // try to get collection
        Collection col = DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION + collection, "admin", "bcd1234");
        if (col == null) {
            // collection does not exist: get root collection and create.
            // for simplicity, we assume that the new collection is a
            // direct child of the root collection, e.g. /db/test.
            // the example will fail otherwise.
            Collection root = DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION, "admin", "bcd1234");
            CollectionManagementService mgtService = (CollectionManagementService) root.getService("CollectionManagementService", "1.0");
            col = mgtService.createCollection(collection);
        }else {
            System.err.println("Collection already exist !");
        }
    }
    public void addFileDB(String collection, String file , String URI) throws Exception {

        Class<?> cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);

        // try to get collection
        Collection col = DatabaseManager.getCollection(URI + XmldbURI.ROOT_COLLECTION +"/" +collection, "admin", "bcd1234");
        Path f = Paths.get(file);
        // create new XMLResource

        XMLResource document = (XMLResource)col.createResource(FileUtils.fileName(f), XMLResource.RESOURCE_TYPE);

        document.setContent(f);
        col.storeResource(document);
    }
}
