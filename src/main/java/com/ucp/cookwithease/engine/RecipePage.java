package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.RecipeForm;
import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.LinkedList;


public class RecipePage extends Page<RecipeForm> {
    private int recipeID;

    public RecipePage(HttpServletRequest request) {
        super(request);

        form = new RecipeForm(request);
    }

    public boolean loadRecipe() {
        recipeID = form.getRecipeIDFromParameter();
        Recipe recipe = DAOFactory.getRecipeDAO().find(recipeID);

        if (recipe == null) {
            form.addGlobalError("La recette recherchée est introuvable.");

            return false;
        }

        LinkedList<Comment> comments = recipe.getComments();

        int userID;
        String pseudo;

        for (Comment comment : comments) {
            userID = comment.getUserID();
            pseudo = DAOFactory.getUserDAO().getPseudoFromId(userID);
            comment.setPseudo(pseudo);
        }

        request.setAttribute("recipe", recipe);

        return true;
    }

    public boolean addBookmark() {
        return false;
    }

    public boolean addComment() {
        String description = form.getComment();
        int rating = form.getRating();
        recipeID = form.getRecipeIDFromComment();

        if (form.hasErrors()) {
            return false;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        boolean isAlreadyCommented = DAOFactory.getCommentDAO().find(user.getId(), recipeID) != null;

        if (isAlreadyCommented) {
            form.addGlobalError("Vous avez déjà commenté et noté cette recette.");

            return false;
        }

        Comment comment = new Comment();

        comment.setUserID(user.getId());
        comment.setRecipeID(recipeID);
        comment.setDescription(description);
        comment.setRating(rating);
        comment.setPublicationDate(new Date());

        boolean hasSucceeded = DAOFactory.getCommentDAO().insert(comment);

        if (!hasSucceeded) {
            form.addGlobalError("Une erreur inconnue s'est produite.");

            return false;

        } else {
            user.addComment(comment);

            return true;
        }
    }

    public boolean loadSuggestions() {
        LinkedList<Recipe> suggestedRecipes = DAOFactory.getRecipeDAO().findAllStarters();

        if (suggestedRecipes.size() == 0) {
            form.addGlobalError("Aucune suggestion.");

            return false;

        } else {
            request.setAttribute("suggestedRecipes", suggestedRecipes);

            return true;
        }
    }

    public int getRecipeID() {
        return recipeID;
    }
}
