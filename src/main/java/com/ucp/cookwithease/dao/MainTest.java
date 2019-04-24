package com.ucp.cookwithease.dao;

import com.ucp.cookwithease.model.User;
import com.ucp.cookwithease.tools.Tools;

import java.util.Date;


public class MainTest {
    public static void main(String[] args) {
        User user = new User();

        user.setFirstName("Tata");
        user.setLastName("Name");
        user.setPseudo("TataName");
        user.setEmail("tata@u-cergy.fr");
        user.setInscriptionDate(new Date());

        System.out.println(DAOFactory.getUserDAO().insert(user, Tools.sha256("password")));
        System.out.println(user.getId());
    }
}
