package com.ucp.xml.parse_xml.prof_xml.profile.dao.profile;

import com.ucp.xml.exist.query.QueryUser;
import com.ucp.xml.parse_xml.user_xml.dao.user.User;
import org.apache.commons.collections.IteratorUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
        List<User> users = queryUser.getAllCompleteUsers();

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
        int starterCount = 0;
        int mainCourseCount = 0;
        int dessertCount = 0;

        for (Map.Entry<Integer, Float> entryUserProfile : userProfile.getEntreeCategories().entrySet()) {
            Integer idUserProfile = entryUserProfile.getKey();
            Float probaUserProfile = entryUserProfile.getValue();
            Float probaUser = user.getEntreeCategories().get(idUserProfile);
            // Si une seule des catégories dépasse la marge return false
            if (probaUserProfile - probaUser >= 0f) {
                if (probaUserProfile - probaUser <= precision) {
                    starterCount++;
                }
            }
            else if (probaUser - probaUserProfile >= 0f) {
                if (probaUser - probaUserProfile <= precision) {
                    starterCount++;
                }
            }
        }
        for (Map.Entry<Integer, Float> entryUserProfile : userProfile.getPlatCategories().entrySet()) {
            Integer idCatProfile = entryUserProfile.getKey();
            Float probaUserProfile = entryUserProfile.getValue();
            Float probaUser = user.getPlatCategories().get(idCatProfile);
            // Si une seule des catégories dépasse la marge return false
            if (probaUserProfile - probaUser >= 0f) {
                if (probaUserProfile - probaUser <= precision) {
                    mainCourseCount++;
                }
            }
            else if (probaUser - probaUserProfile >= 0f) {
                if (probaUser - probaUserProfile <= precision) {
                    mainCourseCount++;
                }
            }
        }
        for (Map.Entry<Integer, Float> entryUserProfile : userProfile.getDessertCategories().entrySet()) {
            Integer idUserProfile = entryUserProfile.getKey();
            Float probaUserProfile = entryUserProfile.getValue();
            Float probaUser = user.getDessertCategories().get(idUserProfile);
            // Si une seule des catégories dépasse la marge return false
            if (probaUserProfile - probaUser >= 0f) {
                if (probaUserProfile - probaUser <= precision) {
                    dessertCount++;
                }
            }
            else if (probaUser - probaUserProfile >= 0f) {
                if (probaUser - probaUserProfile <= precision) {
                    dessertCount++;
                }
            }
        }
        System.out.println(starterCount + " + " + mainCourseCount + " + " + dessertCount);

        return (starterCount >= 1 && (mainCourseCount >= 1 || dessertCount >= 1) || (mainCourseCount >= 1 && dessertCount >= 1));
    }
}
