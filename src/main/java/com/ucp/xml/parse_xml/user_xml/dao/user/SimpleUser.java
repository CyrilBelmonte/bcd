package com.ucp.xml.parse_xml.user_xml.dao.user;

import java.util.HashMap;
import java.util.List;

public class SimpleUser implements User {
    private Integer idUser;
    private HashMap<String, Float> entreeCategories;
    private HashMap<String, Float> platCategories;
    private HashMap<String, Float> dessertCategories;
    private HashMap<Integer, String> friends;
    private HashMap<Integer, String> bookmarks;

    @Override
    public Integer getIdUser() {
        return idUser;
    }

    @Override
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public HashMap<String, Float> getEntreeCategories() {
        return entreeCategories;
    }

    @Override
    public HashMap<String, Float> getPlatCategories() {
        return platCategories;
    }

    @Override
    public HashMap<String, Float> getDessertCategories() {
        return dessertCategories;
    }

    @Override
    public HashMap<Integer, String> getFriends() {
        return friends;
    }

    @Override
    public HashMap<Integer, String> getBookmarks() {
        return bookmarks;
    }

    public void setEntreeCategories(HashMap<String, Float> entreeCategories) {
        this.entreeCategories = entreeCategories;
    }

    public void setPlatCategories(HashMap<String, Float> platCategories) {
        this.platCategories = platCategories;
    }

    public void setDessertCategories(HashMap<String, Float> dessertCategories) {
        this.dessertCategories = dessertCategories;
    }

    public void setFriends(HashMap<Integer, String> friends) {
        this.friends = friends;
    }

    public void setBookmarks(HashMap<Integer, String> bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public String toString() {
        return "SimpleUser{" +
                "idUser=" + idUser +
                ", entreeCategories=" + entreeCategories.size() +
                ", platCategories=" + platCategories.size() +
                ", dessertCategories=" + dessertCategories.size() +
                ", friends=" + friends.size() +
                ", bookmarks=" + bookmarks.size() +
                '}';
    }
}
