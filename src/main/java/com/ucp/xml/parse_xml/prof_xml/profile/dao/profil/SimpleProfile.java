package com.ucp.xml.parse_xml.prof_xml.profile.dao.profil;

import com.ucp.xml.parse_xml.user_xml.dao.user.User;

import java.util.ArrayList;

public class SimpleProfile implements Profile {
    private Integer idProfil;
    private ArrayList<String> users;

    @Override
    public Integer getIdProfil() {
        return null;
    }

    @Override
    public ArrayList<User> getUsers() {
        return null;
    }

    public void setIdProfil(Integer idProfil) {
        this.idProfil = idProfil;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SimpleProfile{" +
                "idProfil=" + idProfil +
                ", users=" + users +
                '}';
    }
}
