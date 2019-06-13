package com.ucp.xml.exist.query.thread.profiles;


import com.ucp.xml.exist.query.QuerySimpleUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadFriends extends Thread {

    private int idUser;
    private String type;

    public ArrayList<Integer> list;


    public ThreadFriends(int idUser, String type) {
        this.idUser = idUser;
        this.type = type;
    }

    @Override
    public void run() {
        Random r = new Random();
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        List<Integer> listFriend = querySimpleUser.friendsList(idUser);
        if (listFriend.size() != 0) {
            findCat(listFriend.get(r.nextInt(listFriend.size())));
        } else {
            list = new ArrayList<>();
        }
    }

    public void findCat(int id) {
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        list = querySimpleUser.findAllCatByOrder(id, type);
    }

    public List<Integer> getList() {
        return list;
    }
}