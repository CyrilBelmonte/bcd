package com.ucp.xml.exist.conf;

public class StartExitDB {
    public static void main(String[] args) throws Exception {
        System.out.println("Init file in eXist DB");
        InitExistDB initExistDB = new InitExistDB("src/main/resources/categories.xml", "src/main/resources/profiles.xml", "src/main/resources/users.xml");
        initExistDB.init();
        System.out.println("File upload in eXist DB");
    }
}
