package com.ucp.xml.parse_xml.prof_xml.profile.dao.profile;

import com.ucp.xml.exist.query.QueryUser;
import com.ucp.xml.parse_xml.user_xml.dao.user.User;
import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Class.forName;

public class DbProfileDao implements  ProfileDao{

    public DbProfileDao(){
        super();
    }

    @Override
    public List<Profile> findAllProfile(Float precision) {
        final List<User> users = dbToProfile(precision);
        final List<Profile> profiles = null;
        return null;
    }

    private List<User> dbToProfile(Float precision) {
        QueryUser queryUser = new QueryUser();
        List<User> users = queryUser.getAllUsers();

        List<Profile> profiles = new ArrayList<>();

        while(users.size() > 0) {
            User user = users.get(0);
            users.remove(0);
            List<User> usersSimilar = new ArrayList<>();
            usersSimilar.add(user);
            int index = 0;

            for (User userProfile : users) {
                if (isSimilar(userProfile, user, precision)) {
                    // Create a new profile
                    Profile profile = new SimpleProfile();
                    profile.setIdProfile(index);

                    // add the user to the list of similar user
                    usersSimilar.add(userProfile);
                    users.remove(userProfile);

                    profile.setUsers(usersSimilar);
                    profiles.add(profile);
                }
            }
            index++;
        }

        return users;
    }

    private boolean isSimilar(User userProfile, User user, Float precision) {
        boolean similar = true;

        for (Map.Entry<String, Float> entryUserProfile : userProfile.getEntreeCategories().entrySet()) {
            String idUserProfile = entryUserProfile.getKey();
            Float probaUserProfile = entryUserProfile.getValue();
            Float probaUser = user.getEntreeCategories().get(idUserProfile);

            // Si une seule des catégories dépasse la marge return falese
            if (probaUserProfile - probaUser > 0) {
                if (probaUserProfile - probaUser > 0.05) {
                    return false;
                }
            }
            else {
                if (probaUser - probaUserProfile < 0.05) {
                    return false;
                }
            }
        }
        return similar;
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
