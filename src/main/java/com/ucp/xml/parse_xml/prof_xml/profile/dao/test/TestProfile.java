package com.ucp.xml.parse_xml.prof_xml.profile.dao.test;

import com.ucp.xml.exist.query.QueryProfile;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.DbProfileDao;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.Profile;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.ProfileDao;

import java.util.List;

public class TestProfile {
    private static ProfileDao profileDao;

    public static void main(String[] args) {
        Float precision = 0.05f;
        Integer idUser = 1;
        String type = "stater";

        profileDao = new DbProfileDao();
        List<Profile> profiles = profileDao.findAllProfile(precision);

        QueryProfile queryProfile = new QueryProfile();
        queryProfile.getProfile(idUser);

        queryProfile.updateProfile();

        queryProfile.suggestRecipesByProfile(idUser, type);
    }
}
