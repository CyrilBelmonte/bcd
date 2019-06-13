package com.ucp.xml.exist.query.thread.profiles;

import com.ucp.xml.exist.query.QuerySimpleUser;

import java.util.List;

public class ThreadUser extends Thread {

    private String type;
    private int idUser;

    public List<Integer> getList() {
        return list;
    }

    private List<Integer> list;

    public ThreadUser(int idUser, String type) {
        this.idUser = idUser;
        this.type = type;
    }

    @Override
    public void run() {
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        list = querySimpleUser.findAllCatByOrder(idUser, type);
    }

}
