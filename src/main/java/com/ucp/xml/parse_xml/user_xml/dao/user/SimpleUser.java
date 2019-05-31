package com.ucp.xml.parse_xml.user_xml.dao.user;

import java.util.HashMap;
import java.util.List;

public class SimpleUser implements User {
    private Integer idUser;
    private HashMap<Integer, Float> entreeCategories;
    private HashMap<Integer, Float> platCategories;
    private HashMap<Integer, Float> dessertCategories;
    private HashMap<Integer, String> friends;
    private HashMap<Integer, String> bookmarks;

    public SimpleUser(Integer idUser) {
        this.idUser = idUser;
        this.entreeCategories = new HashMap<>();
        this.platCategories = new HashMap<>();
        this.dessertCategories = new HashMap<>();
        this.friends = new HashMap<>();
        this.bookmarks = new HashMap<>();
    }
    @Override
    public Integer getIdUser() {
        return idUser;
    }

    @Override
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public HashMap<Integer, Float> getEntreeCategories() {
        return entreeCategories;
    }

    @Override
    public HashMap<Integer, Float> getPlatCategories() {
        return platCategories;
    }

    @Override
    public HashMap<Integer, Float> getDessertCategories() {
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

    @Override
    public void setEntreeCategories(HashMap<Integer, Float> entreeCategories) {
        this.entreeCategories = entreeCategories;
    }

    @Override
    public void setPlatCategories(HashMap<Integer, Float> platCategories) {
        this.platCategories = platCategories;
    }
    @Override
    public void setDessertCategories(HashMap<Integer, Float> dessertCategories) {
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
                ", entreeCategories=" + (entreeCategories.isEmpty() ? 0 : entreeCategories.size()) +
                ", platCategories=" + (platCategories.isEmpty() ? 0 : platCategories.size()) +
                ", dessertCategories=" + (dessertCategories.isEmpty() ? 0 : dessertCategories.size()) +
                ", friends=" + (friends.isEmpty() ? 0 : friends.size()) +
                ", bookmarks=" + (bookmarks.isEmpty() ? 0 : bookmarks.size()) +
                '}';
    }
}
