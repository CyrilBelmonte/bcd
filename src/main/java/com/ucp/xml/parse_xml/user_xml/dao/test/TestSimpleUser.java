package com.ucp.xml.parse_xml.user_xml.dao.test;

import com.ucp.xml.exist.query.QuerySimpleUser;

public class TestSimpleUser {
    public static void main(String[] args) {
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        System.out.println(querySimpleUser.getFirstCategory(1,"starter"));
    }
}
