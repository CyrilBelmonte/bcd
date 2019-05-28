package com.ucp.xml.exist.sample;
import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;

import static java.lang.Class.forName;
/*

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
        Collection col = DatabaseManager.getCollection("xmldb:exist://localhost:8080/exist/xmlrpc/db/categories" ,"admin","bcd1234");

        //Appel au service permettant d’exécuter des requêtes avec XPath
        XPathQueryService service = (XPathQueryService) col.getService("XPathQueryService", "1.0");
        service.setProperty("indent", "yes");

        //Description de la requête
        //this query update de id_r of the recipe on eXistDB
        ResourceSet result = service.query(" let $doc := //category[@type='plat']/recipes/recipe[@id_r='7'] return update value $doc/@dist_r with '5.0'");
        ResourceIterator i = result.getIterator();
        while(i.hasMoreResources()) {
            Resource r = i.nextResource();
            System.out.println((String)r.getContent());
        }
    }
}
*/