package com.ucp.cookwithease.forms;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;


public class RecipeForm extends Form {
    private static final String ID_PARAMETER = "id";
    private static final String ID_COMMENT_PARAMETER = "add-comment";
    private static final int ID_MAX_VALUE = 1000000000;
    private static final String COMMENT_FIELD = "comment";
    private static final int COMMENT_LENGTH = 200;
    private static final String RATING_FIELD = "rating";
    private static final int RATING_MAX_VALUE = 5;

    private int recipeID;

    public Recipe getRecipe(HttpServletRequest request) {
        int id = getIntValueFrom(request, ID_PARAMETER, ID_MAX_VALUE);

        if (id == 0) {
            this.addError(ID_PARAMETER, "L'identifiant de la recette est incorrect.");
            return null;
        }

        Recipe recipe = DAOFactory.getRecipeDAO().find(id);

        if (recipe == null) {
            this.addError(ID_PARAMETER, "La recette recherchée est introuvable.");
            return null;
        }

        LinkedList<Comment> comments = recipe.getComments();

        int userID;
        String pseudo;

        for (Comment comment : comments) {
            userID = comment.getUserID();
            pseudo = DAOFactory.getUserDAO().getPseudoFromId(userID);
            comment.setPseudo(pseudo);
        }

        return recipe;
    }

    public boolean addBookmark(HttpServletRequest request) {
        return false;
    }

    public boolean addComment(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        String description = getValueFrom(request, COMMENT_FIELD, COMMENT_LENGTH);
        int rating = getIntValueFrom(request, RATING_FIELD, RATING_MAX_VALUE);
        recipeID = getIntValueFrom(request, ID_COMMENT_PARAMETER, ID_MAX_VALUE);

        if (recipeID == 0) {
            this.addError(ID_PARAMETER, "L'identifiant de la recette est incorrect.");
        }

        if (description == null) {
            this.addError(COMMENT_FIELD, "Le commentaire est incorrect.");
        }

        if (rating == 0) {
            this.addError(RATING_FIELD, "La note attribuée est incorrecte.");
        }

        if (this.hasErrors()) {
            return false;
        }

        boolean isAlreadyCommented = DAOFactory.getCommentDAO().find(user.getId(), recipeID) != null;

        if (isAlreadyCommented) {
            this.addError("global", "Vous avez déjà commenté et noté cette recette.");
            return false;
        }

        Comment comment = new Comment();

        comment.setUserID(user.getId());
        comment.setRecipeID(recipeID);
        comment.setDescription(description);
        comment.setRating(rating);
        comment.setPublicationDate(new Date());

        boolean hasSucceeded = DAOFactory.getCommentDAO().insert(comment);

        if (hasSucceeded) {
            user.addComment(comment);
            return true;

        } else {
            return false;
        }
    }

    public int getRecipeID() {
        return recipeID;
    }
}
