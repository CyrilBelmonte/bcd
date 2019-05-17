package com.ucp.xml.exist.conf;

public class StartExitDB {
    public static void main(String[] args) throws Exception{
        InitExistDB initExistDB = new InitExistDB("src/main/resources/categories.xml","src/main/resources/profiles.xml","src/main/resources/users.xml");
        initExistDB.init();
    }
}
