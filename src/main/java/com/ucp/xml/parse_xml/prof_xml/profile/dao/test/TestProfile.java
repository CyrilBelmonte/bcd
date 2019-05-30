package com.ucp.xml.parse_xml.prof_xml.profile.dao.test;

import com.ucp.xml.exist.query.QueryProfile;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.DbProfileDao;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.Profile;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.ProfileDao;

import java.util.List;

public class TestProfile {
    private static ProfileDao profileDao;

    public static void main(String[] args) {
        Float precision = 1f;
        Integer idUser = 1;
        String type = "starter";

        profileDao = new DbProfileDao();
        List<Profile> profiles = profileDao.findAllProfile(precision);
        QueryProfile queryProfile = new QueryProfile();
        System.out.println("ERASE");
        queryProfile.removeAll();
        System.out.println("PRINT");
        queryProfile.printAllProfile();
        System.out.println("ADD");
        queryProfile.addProfiles(profiles);

        queryProfile.getProfile(idUser);

        queryProfile.updateProfile();

        queryProfile.suggestRecipesByProfile(idUser, type);
    }
}
