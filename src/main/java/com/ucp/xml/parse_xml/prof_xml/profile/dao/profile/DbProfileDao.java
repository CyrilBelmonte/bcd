package com.ucp.xml.parse_xml.prof_xml.profile.dao.profile;

import com.ucp.xml.exist.query.QueryUser;
import com.ucp.xml.parse_xml.user_xml.dao.user.User;
import org.apache.commons.collections.IteratorUtils;
import org.apache.log4j.BasicConfigurator;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.XPathQueryService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static java.lang.Class.forName;

public class DbProfileDao implements  ProfileDao{

    public DbProfileDao(){
        super();
    }

    @Override
    public List<Profile> findAllProfile(Float precision) {
        final List<Profile> profiles = dbToProfile(precision);
        return profiles;
    }

    private List<Profile> dbToProfile(Float precision) {
        int index = 0;
        QueryUser queryUser = new QueryUser();
        List<User> users = queryUser.getAllUsers();
        List<User> usersRemain;
        Iterator<User> userIterator = users.iterator(); // Pour supprimer pendant le parcours de la lite
        List<Profile> profiles = new ArrayList<>();

        // Parcours tous les Users tant que la liste n'est pas vide
        while(userIterator.hasNext()) {
            User user = userIterator.next(); // Récupère le 1er User et le mets dans une var temp
            userIterator.remove(); // Enlève le 1er User de la liste
            Profile profile = new SimpleProfile(); // Créer un nouveau profil
            profile.setIdProfile(index);
            // Créer une liste de User d'un profil similaire
            List<User> usersSimilar = new ArrayList<>();
            usersSimilar.add(user); // Ajoute le 1er User

            usersRemain = IteratorUtils.toList(userIterator); // Pour supprimer un élément de la liste pendant son parcours
            // Parcourir le reste de la liste Users
            for (User userRemaining : users) {
                // Compare le 1er User à chaque autre User avec une marge (Ex. 5%)
                if (isSimilar(userRemaining, user, precision)) {
                    usersSimilar.add(userRemaining); // Ajoute le User similaire à la liste des similaires
                    usersRemain.remove(userRemaining); // Supprime le User similaire de la liste des Users
                }
            }
            profile.setUsers(usersSimilar);
            profiles.add(profile);
            index++;
        }

        return profiles;
    }

    private boolean isSimilar(User userProfile, User user, Float precision) {
        boolean similar = true;

        for (Map.Entry<String, Float> entryUserProfile : userProfile.getEntreeCategories().entrySet()) {
            String idUserProfile = entryUserProfile.getKey();
            Float probaUserProfile = entryUserProfile.getValue();
            Float probaUser = user.getEntreeCategories().get(idUserProfile);
            // Si une seule des catégories dépasse la marge return false
            if (probaUserProfile - probaUser > 0f) {
                if (probaUserProfile - probaUser > precision) {
                    similar = false;
                }
            }
            else if (probaUser - probaUserProfile > 0f) {
                if (probaUser - probaUserProfile < precision) {
                    similar = false;
                }
            } else {
                similar = true;
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
