package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.ProfileForm;
import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;


public class ProfilePage extends Page<ProfileForm> {
    private int profileID;

    public ProfilePage(HttpServletRequest request) {
        super(request);

        form = new ProfileForm(request);
    }

    public boolean loadProfile() {
        profileID = form.getProfileIDFromParameter();
        User profile = DAOFactory.getUserDAO().find(profileID);

        if (profile == null) {
            form.addGlobalError("L'utilisateur recherché est introuvable.");

            return false;
        }

        LinkedList<Comment> comments = profile.getComments();

        int recipeID;
        String recipeName;

        for (Comment comment : comments) {
            recipeID = comment.getRecipeID();
            recipeName = DAOFactory.getRecipeDAO().getNameFromId(recipeID);
            comment.setRecipeName(recipeName);
        }

        request.setAttribute("profile", profile);

        return true;
    }

    public boolean follow() {
        return false;
    }

    public int getProfileID() {
        return profileID;
    }
}
