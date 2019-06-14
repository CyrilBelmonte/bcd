package com.ucp.xml.exist.query.thread.profiles;

import com.ucp.xml.exist.query.QueryProfile;
import com.ucp.xml.exist.query.QuerySimpleUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThreadProfiles extends Thread {

    private ArrayList<Integer> list;
    private int idUser;
    private String type;

    public ThreadProfiles(int idUser, String type) {
        this.idUser = idUser;
        this.type = type;
    }

    @Override
    public void run() {
        Random r = new Random();
        QueryProfile queryProfile = new QueryProfile();
        List<Integer> listProfiles = queryProfile.getIdUsersByIdUser(idUser);
        listProfiles.remove(listProfiles.indexOf(idUser));
        if (listProfiles.size() != 0) {
            findCat(listProfiles.get(r.nextInt(listProfiles.size())));
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
