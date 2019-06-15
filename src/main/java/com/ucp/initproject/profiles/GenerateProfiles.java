package com.ucp.initproject.profiles;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;


public class GenerateProfiles {
    private static final String usersFile = "./src/main/java/com/ucp/initproject/profiles/users/users.yaml";

    private GenerateProfiles() {}

    public static boolean generate() {
        Yaml yaml = new Yaml();
        ProfileGenerator profileGenerator = new ProfileGenerator();

        String pseudo;
        String firstName;
        String lastName;

        LinkedList<String> favoriteStarters;
        LinkedList<String> favoriteMainCourses;
        LinkedList<String> favoriteDesserts;

        InputStream inputStream;
        HashMap<String, Object> profile;
        HashMap<String, HashMap<String, Object>> allProfiles;

        try {
            inputStream = new FileInputStream(new File(usersFile));
            allProfiles = yaml.load(inputStream);

        } catch (FileNotFoundException e) {
            System.err.println("[ERROR] GenerateProfiles : " + e.getMessage());
            return false;
        }

        for (Map.Entry<String, HashMap<String, Object>> profileSet : allProfiles.entrySet()) {
            profile = profileSet.getValue();

            if (!profile.containsKey("firstName") ||
                !profile.containsKey("lastName") ||
                !profile.containsKey("favoriteStarters") ||
                !profile.containsKey("favoriteMainCourses") ||
                !profile.containsKey("favoriteDesserts")) {

                System.err.println("[ERROR] The file is wrong!");
                return false;
            }

            pseudo = profileSet.getKey();
            firstName = (String) profile.get("firstName");
            lastName = (String) profile.get("lastName");

            favoriteStarters = new LinkedList<>(
                (List<String>) profile.get("favoriteStarters"));

            favoriteMainCourses = new LinkedList<>(
                (List<String>) profile.get("favoriteMainCourses"));

            favoriteDesserts = new LinkedList<>(
                (List<String>) profile.get("favoriteDesserts"));

            profileGenerator.newProfile();

            profileGenerator.setFirstName(firstName);
            profileGenerator.setLastName(lastName);
            profileGenerator.setPseudo(pseudo);

            profileGenerator.setFavoriteStarters(favoriteStarters);
            profileGenerator.setFavoriteMainCourses(favoriteMainCourses);
            profileGenerator.setFavoriteDesserts(favoriteDesserts);

            profileGenerator.generate();
        }

        return true;
    }

    public static void main(String[] args) {
        generate();
    }
}
