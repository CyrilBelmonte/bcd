package com.ucp.xml.parse_xml.user_xml.dao.user;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.xml.exist.query.InitConnection;
import com.ucp.xml.exist.query.QueryCategory;
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

    private User dbToUser(int idUser, LinkedList<Integer> friends, LinkedList<Integer> bookmarks) {
        SimpleUser user = new SimpleUser(idUser);

        QueryCategory queryCategory = new QueryCategory();
        // Categories of the user
        try {
            user.setEntreeCategories(queryCategory.findCategoriesByType("starter"));
            user.setPlatCategories(queryCategory.findCategoriesByType("main_Courses"));
            user.setDessertCategories(queryCategory.findCategoriesByType("dessert"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Friends of the user
        HashMap<Integer, String> friendsDao = new HashMap<Integer, String>();
        int indexFriend = 0;
        for (Integer friendID : friends) {
            friendsDao.put(indexFriend, String.valueOf(friendID));
            indexFriend++;
        }
        user.setFriends(friendsDao);
        // Bookmarks of the user
        HashMap<Integer, String> bookmarksDao = new HashMap<Integer, String>();
        int indexBookmarks = 0;
        for (Integer recipeId : bookmarks) {
            bookmarksDao.put(indexBookmarks, String.valueOf(recipeId));
            indexBookmarks++;
        }
        user.setBookmarks(bookmarksDao);
        return user;
    }

    private static HashMap<Integer, String> existDBConnection(String xPathQuery) throws Exception {
        InitConnection connection = new InitConnection();


        //Accès à la collection
        Collection col = connection.getCollection();

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
