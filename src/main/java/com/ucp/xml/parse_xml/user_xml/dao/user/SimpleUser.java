package com.ucp.xml.parse_xml.user_xml.dao.user;

import java.util.HashMap;
import java.util.List;

public class SimpleUser implements User {
    private Integer idUser;
    private HashMap<Integer, String> entreeCategories;
    private HashMap<Integer, String> platCategories;
    private HashMap<Integer, String> dessertCategories;
    private HashMap<Integer, String> friends;
    private HashMap<Integer, String> bookmarks;

    @Override
    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public HashMap<Integer, String> getEntreeCategories() {
        return entreeCategories;
    }

    @Override
    public HashMap<Integer, String> getPlatCategories() {
        return platCategories;
    }

    @Override
    public HashMap<Integer, String> getDessertCategories() {
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

    public void setEntreeCategories(HashMap<Integer, String> entreeCategories) {
        this.entreeCategories = entreeCategories;
    }

    public void setPlatCategories(HashMap<Integer, String> platCategories) {
        this.platCategories = platCategories;
    }

    public void setDessertCategories(HashMap<Integer, String> dessertCategories) {
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
