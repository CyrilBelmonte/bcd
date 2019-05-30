package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.ProfileForm;
import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.User;
import com.ucp.xml.exist.query.QuerySimpleUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        request.setAttribute("profile", profile);
        request.setAttribute("isFriend", user.hasFriend(profileID) || user.getId() == profileID);

        return true;
    }

    public boolean follow() {
        profileID = form.getProfileIDFromFollow();

        if (form.hasErrors()) {
            return false;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        user.addFriend(profileID);

        QuerySimpleUser query = new QuerySimpleUser();
        query.addFriend(user.getId(), profileID);

        return true;
    }

    public int getProfileID() {
        return profileID;
    }
}
