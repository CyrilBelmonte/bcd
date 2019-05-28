package com.ucp.xml.parse_xml.prof_xml.profile.dao.profil;

import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Class.forName;

public class DbProfilDao implements  ProfileDao{

    public DbProfilDao(){
        super();
    }

    @Override
    public List<Profile> findAllProfil() {
        return null;
    }

    private static ArrayList<String> eXistDbConnection(String xPathQuery) throws Exception {
        BasicConfigurator.configure();
        String driver = "org.exist.xmldb.DatabaseImpl";

        Class cl = forName(driver);
        Database database = (Database)cl.newInstance();
        DatabaseManager.registerDatabase(database);

        // Accès à la collection
        Collection col = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/categories");

        //Appel au service permettant d’exécuter des requêtes avec XPath
        XPathQueryService service = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        service.setProperty("indent", "yes");

        ArrayList<String> resultQuery = new ArrayList<>();
        ResourceSet result = service.query("//users/user");
        ResourceIterator i = result.getIterator();

        int index = 0;
        while(i.hasMoreResources()) {
            Resource r = i.nextResource();
            resultQuery.add(r.getContent().toString());
            index++;
//            System.out.println((String)r.getContent());
        }
        return resultQuery;
    }
}
