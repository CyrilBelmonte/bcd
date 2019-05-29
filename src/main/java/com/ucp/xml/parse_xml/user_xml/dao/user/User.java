package com.ucp.xml.parse_xml.user_xml.dao.user;

import java.util.HashMap;

public interface User {
    Integer getIdUser();
    void setIdUser(int idUser);
    HashMap<String, Float> getEntreeCategories();
    HashMap<String, Float> getPlatCategories();
    HashMap<String, Float> getDessertCategories();
    HashMap<Integer, String> getFriends();
    HashMap<Integer, String> getBookmarks();
}
