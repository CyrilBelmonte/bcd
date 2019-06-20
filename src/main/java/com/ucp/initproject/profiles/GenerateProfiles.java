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

        ProfileGenerator profileGenerator;
        LinkedList<ProfileGenerator> profileGenerators = new LinkedList<>();

        String pseudo;
        String firstName;
        String lastName;

        LinkedList<String> favoriteStarters;
        LinkedList<String> favoriteMainCourses;
        LinkedList<String> favoriteDesserts;

        HashMap<String, Object> profile;
        HashMap<String, HashMap<String, Object>> allProfiles;

        try {
            InputStream inputStream = new FileInputStream(new File(usersFile));
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

            profileGenerator = new ProfileGenerator();

            profileGenerator.setFirstName(firstName);
            profileGenerator.setLastName(lastName);
            profileGenerator.setPseudo(pseudo);

            profileGenerator.setFavoriteStarters(favoriteStarters);
            profileGenerator.setFavoriteMainCourses(favoriteMainCourses);
            profileGenerator.setFavoriteDesserts(favoriteDesserts);

            profileGenerators.add(profileGenerator);
        }

        Thread thread;
        LinkedList<Thread> threads = new LinkedList<>();

        for (int i = 0; i < profileGenerators.size(); i++) {
            profileGenerator = profileGenerators.get(i);

            thread = new Thread(profileGenerator::generate);
            thread.start();
            threads.add(thread);

            if (threads.size() == 4 || i == profileGenerators.size() - 1) {
                while (threads.size() > 0) {
                    thread = threads.remove(0);

                    try {
                        thread.join();

                    } catch (InterruptedException e) {
                        // Nothing
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        generate();
    }
}
