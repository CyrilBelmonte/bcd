package com.ucp.xml.parse_xml.prof_xml.profile.dao.profil;


import com.ucp.xml.parse_xml.user_xml.dao.user.User;

import java.util.ArrayList;

public interface Profile {
    Integer getIdProfil();
    ArrayList<User> getUsers();
}
