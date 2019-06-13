package com.ucp.xml.exist.query.thread.profiles;

import java.util.HashMap;
import java.util.List;

public class ThreadList {

    private int idUser;
    private String type;

    private HashMap<String, List<Integer>> category;

    public ThreadList(int idUser, String type) {
        this.idUser = idUser;
        this.type = type;
        findCat();
    }

    public HashMap<String, List<Integer>> getCategory() {
        return category;
    }

    public void findCat() {
        ThreadFriends friends = new ThreadFriends(idUser, type);
        //ThreadProfiles profiles = new ThreadProfiles(idUser, type);
        ThreadUser users = new ThreadUser(idUser, type);

        friends.start();
        //profiles.start();
        users.start();
        try {
            friends.join();
           // profiles.join();
            users.join();

            category.put("user", users.getList());
            category.put("friend", friends.getList());
            //category.put("profile", friends.getList());

        } catch (Exception e) {
            System.err.println("[ERROR] join");
            e.printStackTrace();
        }
    }
}
