package com.ucp.xml.sample;
import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;

import static java.lang.Class.forName;

//import;

public class QueryExample {

    public static void main(String args[]) throws Exception {

        BasicConfigurator.configure();
        String driver = "org.exist.xmldb.DatabaseImpl";

        //déterminer la classe de driver utilisée
        //Pour se connecter à une base de données il est essentiel de charger dans un premier temps le
        //pilote de la base de données à laquelle on désire se connecter grâce à un appel au
        // DriverManager (gestionnaire de pilotes) : Class.forName("nom.de.la.classe");
        // Cette instruction charge le pilote et crée une instance de cette classe.

        Class cl = forName(driver);
        Database database = (Database)cl.newInstance();
        DatabaseManager.registerDatabase(database);

        //Accès à la collection
        Collection col = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/categories" );

        //Appel au service permettant d’exécuter des requêtes avec XPath

        XPathQueryService service = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        service.setProperty("indent", "yes");

        //Description de la requête

        ResourceSet result = service.query("//category[@type='plat']/reciepes/reciepe");
        ResourceIterator i = result.getIterator();
        while(i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String)r.getContent());
        }
    }
}
