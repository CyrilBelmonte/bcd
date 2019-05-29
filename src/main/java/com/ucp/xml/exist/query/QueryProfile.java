package com.ucp.xml.exist.query;

import com.ucp.cookwithease.model.Recipe;
import com.ucp.xml.parse_xml.prof_xml.profile.dao.profile.Profile;
import org.xmldb.api.base.Collection;

import java.util.List;

public class QueryProfile {
    private Collection collection;

    public QueryProfile(){
        try {
            InitConnection connection = new InitConnection();
            collection = connection.getCollection();
        }catch (Exception e){
            System.err.println("[ERROR] : "+e);
            collection = null;
        }
    }

    public void addProfile(Profile profile) {

    }

    public void addProfiles(List<Profile> profiles) {

    }

    public List<Profile> getProfile(int idUser) {
        return null;
    }

    public boolean updateProfile() {
        return false;
    }

    public List<Recipe> suggestRecipesByProfile(int idUser, String type) {
        return null;
    }
}
