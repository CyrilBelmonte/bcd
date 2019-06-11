package com.ucp.xml.profiles;

import com.ucp.xml.exist.query.QuerySimpleUser;

import java.util.ArrayList;

public class UserListWithAll {

    private ArrayList<User4Prof> user4Profs = new ArrayList<>();

    public UserListWithAll() {
        generateList();
    }

    private void generateList() {
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();

        ArrayList<Integer> userList = querySimpleUser.getUserList();

        for (Integer idUser : userList) {
            User4Prof user4Prof = new User4Prof(idUser);
            this.user4Profs.add(user4Prof);
        }
    }

    public ArrayList<User4Prof> getUser4Profs() {
        return user4Profs;
    }

}
