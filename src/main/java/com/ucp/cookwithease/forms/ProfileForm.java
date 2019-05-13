package com.ucp.cookwithease.forms;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;


public class ProfileForm extends Form {
    private static final String ID_PARAMETER = "id";
    private static final String ID_COMMENT_PARAMETER = "add-friend";
    private static final int ID_MAX_VALUE = 1000000000;

    public User getProfile(HttpServletRequest request) {
        int id = getIntValueFrom(request, ID_PARAMETER, ID_MAX_VALUE);

        if (id == 0) {
            this.addError(ID_PARAMETER, "L'identifiant de l'utilisateur est incorrect.");
            return null;
        }

        User profile = DAOFactory.getUserDAO().find(id);

        if (profile == null) {
            this.addError(ID_PARAMETER, "L'utilisateur recherché est introuvable.");
            return null;
        }

        LinkedList<Comment> comments = profile.getComments();

        int recipeID;
        String recipeName;

        for (Comment comment : comments) {
            recipeID = comment.getRecipeID();
            recipeName = DAOFactory.getRecipeDAO().getNameFromId(recipeID);
            comment.setRecipeName(recipeName);
        }

        return profile;
    }

    public boolean follow(HttpServletRequest request) {
        return false;
    }
}
