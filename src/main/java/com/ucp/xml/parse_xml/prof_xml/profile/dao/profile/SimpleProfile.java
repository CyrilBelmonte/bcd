package com.ucp.xml.parse_xml.prof_xml.profile.dao.profile;

import com.ucp.xml.parse_xml.user_xml.dao.user.User;

import java.util.List;

public class SimpleProfile implements Profile {
    private Integer idProfile;
    private List<User> users;

    @Override
    public Integer getIdProfile() {
        return idProfile;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void setIdProfile(Integer idProfile) {
        this.idProfile = idProfile;
    }

    @Override
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SimpleProfile{" +
                "idProfile=" + idProfile +
                ", users=" + users +
                '}';
    }
}
