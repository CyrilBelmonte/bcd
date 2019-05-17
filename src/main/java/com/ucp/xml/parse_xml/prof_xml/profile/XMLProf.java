package com.ucp.xml.parse_xml.prof_xml.profile;

import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.util.List;

import static java.lang.Class.forName;

public class XMLProf {

    private List<User> user;

    public XMLProf(){
        super();
    }

    public List<User> usersList() throws Exception{

        BasicConfigurator.configure();
        String driver = "org.exist.xmldb.DatabaseImpl";

        Class cl = forName(driver);
        Database database = (Database)cl.newInstance();

        DatabaseManager.registerDatabase(database);

        Collection col = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/categories" ,"admin","bcd1234");

        //Appel au service permettant d’exécuter des requêtes avec XPath
        XPathQueryService service = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        service.setProperty("indent", "yes");

        ResourceSet result = service.query("//users/user");
        ResourceIterator i = result.getIterator();
        while(i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String)r.getContent());
        }

        return null;
    }

}
