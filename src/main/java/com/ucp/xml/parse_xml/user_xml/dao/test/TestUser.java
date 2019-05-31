package com.ucp.xml.parse_xml.user_xml.dao.test;

import com.ucp.xml.exist.query.QueryUser;
import com.ucp.xml.parse_xml.user_xml.dao.user.DbUserDao;
import com.ucp.xml.parse_xml.user_xml.dao.user.SimpleUser;
import com.ucp.xml.parse_xml.user_xml.dao.user.User;
import com.ucp.xml.parse_xml.user_xml.dao.user.UserDao;

import java.util.List;

public class TestUser {
    private static UserDao userDao;

    public static void main(String[] args) {
        userDao = new DbUserDao();
        List<User> users = userDao.findAllUser();

        User user = new SimpleUser(5);


        QueryUser queryUser = new QueryUser();
        System.out.println("ERASE");
        queryUser.removeAll();
        System.out.println("PRINT");
        queryUser.printAllUser();
        System.out.println("ADD");
        queryUser.addUsers(users);

        queryUser.addUser(user);
        queryUser.addSimpleUser(6);

        for (int i=0; i<users.size(); i++) {
            System.out.println("Index: " + i +
                    " User id:" + users.get(i).getIdUser() +
                    " Nombre de catégorie entrée " + users.get(i).getEntreeCategories().size() +
                    " Nombre de catégorie plat " + users.get(i).getPlatCategories().size() +
                    " Nombre de catégorie dessert " + users.get(i).getDessertCategories().size() +
                    " Nombre d'amis " + users.get(i).getFriends().size() +
                    " Nombre de recette préféré " + users.get(i).getBookmarks().size());
        }
    }
}
