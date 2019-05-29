package com.ucp.xml.exist.conf;


public class InitExistDB {

    private String collectionPath = "bcd";
    private String URIDB = "xmldb:exist://localhost:8080/exist/xmlrpc";

    private String fileCategoryPath, fileProfilePath, fileUserPath;

    public InitExistDB(String fileCategoryPath, String fileProfilePath, String fileUserPath) {
        this.fileCategoryPath = fileCategoryPath;
        this.fileProfilePath = fileProfilePath;
        this.fileUserPath = fileUserPath;
    }

    public void init() throws Exception {

        CollectionDB collectionDB = new CollectionDB();

        collectionDB.addCollectionDB(collectionPath, URIDB);

        collectionDB.addFileDB(collectionPath, fileCategoryPath, URIDB);
        collectionDB.addFileDB(collectionPath, fileProfilePath, URIDB);
        collectionDB.addFileDB(collectionPath, fileUserPath, URIDB);
    }


}
