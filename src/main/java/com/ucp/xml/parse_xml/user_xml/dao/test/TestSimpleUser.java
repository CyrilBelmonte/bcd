package com.ucp.xml.parse_xml.user_xml.dao.test;

import com.ucp.xml.exist.query.QueryCategory;
import com.ucp.xml.exist.query.QuerySimpleUser;

public class TestSimpleUser {
    public static void main(String[] args) {

        /**
         * All method are comment in class
         */
        QuerySimpleUser querySimpleUser = new QuerySimpleUser();
        QueryCategory queryCategory = new QueryCategory();

        System.out.println(querySimpleUser.getFirstCategory(1, "main_course"));
        querySimpleUser.majCat(1, 22, 5);
        System.out.println(querySimpleUser.getFirstCategory(1, "main_course"));
        //System.out.println(queryCategory.findCatByRecipe(22));
    }
}
