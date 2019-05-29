package com.ucp.xml.parse_xml.prof_xml.profile.dao.profile;


import com.ucp.xml.parse_xml.user_xml.dao.user.User;

import java.util.List;

public interface Profile {
    Integer getIdProfile();
    void setIdProfile(Integer idProfile);
    List<User> getUsers();
    void setUsers(List<User> users);
}
