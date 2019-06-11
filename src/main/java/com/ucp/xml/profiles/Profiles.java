package com.ucp.xml.profiles;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.sqrt;

public class Profiles {
    ArrayList<ArrayList<Integer>> profilesList = new ArrayList<>();

    public Profiles(double d) {
        generateProfiles(d);
    }

    private void generateProfiles(double d) {

        UserListWithAll usersList = new UserListWithAll();

        while (usersList.getUser4Profs().size() != 0) {

            Random random = new Random();
            int index = random.nextInt(usersList.getUser4Profs().size());

            User4Prof user = usersList.getUser4Profs().get(index);

            usersList.getUser4Profs().remove(index);

            ArrayList<Integer> profile = new ArrayList<>();
            profile.add(user.getUser());

            int size = usersList.getUser4Profs().size();

            for (int indexList = 0; indexList < size; indexList++) {

                User4Prof user4Prof = usersList.getUser4Profs().get(indexList);

                double distanceStarter = distance(user.getCatStarter(), user4Prof.getCatStarter());
                double distanceMainCourse = distance(user.getCatMainCourse(), user4Prof.getCatMainCourse());
                double distanceDessert = distance(user.getCatDessert(), user4Prof.getCatDessert());

                Boolean bool = similar(d, distanceStarter, distanceMainCourse, distanceDessert);
                if (bool) {
                    profile.add(user4Prof.getUser());
                    usersList.getUser4Profs().remove(indexList);
                    size--;
                    indexList--;
                }
            }
            profilesList.add(profile);
        }
    }

    private Double distance(ArrayList<Double> user1, ArrayList<Double> user2) {
        Double distance = 0.0;

        for (int index = 0; index < user1.size(); index++) {
            double delta = user2.get(index) - user1.get(index);
            delta = delta*delta;
            distance = distance + delta;
        }
        return sqrt(distance);
    }

    private boolean similar(double percent, double distanceStarter, double distanceMainCourse, double distanceDessert) {

        double per = 1-(percent / 100);
        double distRefStarter = distanceStarter / sqrt(2);
        double distRefMainCourse = distanceMainCourse / sqrt(2);
        double distRefDessert = distanceDessert / sqrt(2);

        if (distRefMainCourse <= per && distRefStarter <= per) {
            return true;
        } else if (distRefMainCourse <= per && distRefDessert <= per) {
            return true;
        } else if (distRefStarter <= per && distRefDessert <= per) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<ArrayList<Integer>> getProfilesList() {
        return profilesList;
    }
}
