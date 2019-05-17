package com.ucp.xml.parse_xml.user_xml.dao.test;

import com.ucp.xml.parse_xml.user_xml.bdtoxml.DbXML;
import com.ucp.xml.parse_xml.user_xml.dao.user.DbUserDao;
import com.ucp.xml.parse_xml.user_xml.dao.user.User;
import com.ucp.xml.parse_xml.user_xml.dao.user.UserDao;

import java.util.List;

public class TestUser {
    private static UserDao userDao;

    public static void main(String[] args) {
        userDao = new DbUserDao();
        List<User> users = userDao.findAllUser();
        DbXML dbXML = new DbXML(users);

        for (int i=0; i<users.size(); i++) {
            System.out.println("Index: " + i +
                    " User id:" + users.get(i).getIdUser() +
                    " Nombre de catégorie entrée " + users.get(i).getEntreeCategories().size() +
                    " Nombre de catégorie plat " + users.get(i).getPlatCategories().size() +
                    " Nombre de catégorie dessert " + users.get(i).getDessertCategories().size() +
                    " Friend: " + users.get(i).getFriends() +
                    " Nombre de recette préféré " + users.get(i).getBookmarks().size());
        }

        dbXML.createDocumentXML();
    }
}
