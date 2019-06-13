package com.ucp.xml.exist.query.thread.profiles;

import com.ucp.xml.exist.query.QueryProfile;
import com.ucp.xml.exist.query.QuerySimpleUser;

import java.util.List;
import java.util.Random;

public class ThreadProfiles extends Thread{

    private List<Integer> list;
    private int idUser;
    private String type;

    public ThreadProfiles(int idUser,String type){
        this.idUser=idUser;
        this.type=type;
    }

    @Override
    public void run(){
        Random r = new Random();
        QueryProfile queryProfile = new QueryProfile();
        List<Integer> list = queryProfile.getIdUsersByIdProfile(idUser);
        System.out.println(list.size());
        findCat(list.get(r.nextInt(list.size())));
    }

    public void findCat(int id){
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        list = querySimpleUser.findAllCatByOrder(id,type);
    }

    public List<Integer> getList() {
        return list;
    }
}
