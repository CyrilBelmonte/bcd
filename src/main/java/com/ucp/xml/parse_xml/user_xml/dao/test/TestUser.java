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

        QueryUser queryUser = new QueryUser();
        System.out.println("ERASE");
        queryUser.removeAll();

        System.out.println("PRINT");
        queryUser.printAllUser();

        System.out.println("ADD");
        queryUser.addSimpleUser(8);
    }
}
