package com.ucp.xml.parse_xml.prof_xml.profile.dao.test;

import com.ucp.xml.exist.query.QueryProfile;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.DbProfileDao;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.Profile;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.ProfileDao;
import com.ucp.xml.profiles.Profiles;

import java.util.List;

public class TestProfile {
    private static ProfileDao profileDao;

    public static void main(String[] args) {
        Profiles profiles = new Profiles(100.0);
        QueryProfile queryProfile = new QueryProfile();

        System.out.println("PRINT");
        queryProfile.printAllProfile();

        System.out.println("ADD");
        queryProfile.addProfiles(profiles.getProfilesList());

        System.out.println("Profil pour User 5 : " + queryProfile.getIdProfileByIdUser(5));

        System.out.println("Profil 0 contient les users : " + queryProfile.getIdUsersByIdProfile(0).toString());

        System.out.println("Users du user 5 : " + queryProfile.getIdUsersByIdUser(5).toString());
    }
}
