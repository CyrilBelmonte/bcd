package com.ucp.xml.parse_xml.user_xml.dao.user;

import java.util.HashMap;

public interface User {
    Integer getIdUser();
    void setIdUser(int idUser);
    HashMap<Integer, Float> getEntreeCategories();
    HashMap<Integer, Float> getPlatCategories();
    HashMap<Integer, Float> getDessertCategories();
    HashMap<Integer, String> getFriends();
    HashMap<Integer, String> getBookmarks();
    void setEntreeCategories(HashMap<Integer, Float> categories);
    void setPlatCategories(HashMap<Integer, Float> categories);
    void setDessertCategories(HashMap<Integer, Float> categories);
}
