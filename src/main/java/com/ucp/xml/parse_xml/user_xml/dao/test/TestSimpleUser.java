package com.ucp.xml.parse_xml.user_xml.dao.test;

import com.ucp.xml.exist.query.QuerySimpleUser;

public class TestSimpleUser {
    public static void main(String[] args) {
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        for(int index = 0 ; index<5;index++){
            querySimpleUser.majCat("1","376",2);
        }
    }
}
