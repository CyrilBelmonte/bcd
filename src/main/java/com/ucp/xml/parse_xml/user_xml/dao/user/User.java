package com.ucp.xml.parse_xml.user_xml.dao.user;

import java.util.HashMap;

public interface User {
    Integer getIdUser();
    HashMap<Integer, String> getEntreeCategories();
    HashMap<Integer, String> getPlatCategories();
    HashMap<Integer, String> getDessertCategories();
    HashMap<Integer, String> getFriends();
    HashMap<Integer, String> getBookmarks();
}
