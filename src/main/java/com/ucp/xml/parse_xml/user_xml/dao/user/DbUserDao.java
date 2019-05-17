package com.ucp.xml.parse_xml.user_xml.dao.user;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Recipe;
import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.base.Collection;
import org.xmldb.api.modules.XPathQueryService;

import java.util.*;

import static java.lang.Class.forName;

public class DbUserDao implements UserDao {

    public DbUserDao() {
        super();
    }



    @Override
    public List<User> findAllUser() {
        // Get all the users from the DataBase
        final List<com.ucp.cookwithease.model.User> users = DAOFactory.getUserDAO().findAll();
        final List<User> usersDao = new ArrayList<User>();

        for (com.ucp.cookwithease.model.User user : users) {
            User userDao = dbToUser(user.getId(), user.getFriends(), user.getBookmarks());
            usersDao.add(userDao);
        }
        return usersDao;
    }

    private User dbToUser(int idUser, LinkedList<com.ucp.cookwithease.model.User> friends, LinkedList<Recipe> bookmarks) {
        SimpleUser user = new SimpleUser();

        // Id of the user
        user.setIdUser(idUser);
        // Categories of the user
        try {
            user.setEntreeCategories(existDBConnection("//category[@type='entree']/@id/string()"));
            user.setPlatCategories(existDBConnection("//category[@type='plat']/@id/string()"));
            user.setDessertCategories(existDBConnection("//category[@type='dessert']/@id/string()"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Friends of the user
        HashMap<Integer, String> friendsDao = new HashMap<Integer, String>();
        int indexFriend = 0;
        for (com.ucp.cookwithease.model.User userModel : friends) {
            friendsDao.put(indexFriend, String.valueOf(userModel.getId()));
            indexFriend++;
        }
        user.setFriends(friendsDao);
        // Bookmarks of the user
        HashMap<Integer, String> bookmarksDao = new HashMap<Integer, String>();
        int indexBookmarks = 0;
        for (Recipe recipe : bookmarks) {
            bookmarksDao.put(indexBookmarks, String.valueOf(recipe.getId()));
            indexBookmarks++;
        }
        user.setBookmarks(bookmarksDao);
        return user;
    }

    private static HashMap<Integer, String> existDBConnection(String xPathQuery) throws Exception {
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

        HashMap<Integer, String> resultQuery = new HashMap<Integer, String>();
        ResourceSet result = service.query(xPathQuery);
        ResourceIterator i = result.getIterator();

        int index = 0;
        while(i.hasMoreResources()) {
            Resource r = i.nextResource();
            resultQuery.put(index, (String)r.getContent());
            index++;
            // System.out.println((String)r.getContent());
        }
        return resultQuery;
    }
}
