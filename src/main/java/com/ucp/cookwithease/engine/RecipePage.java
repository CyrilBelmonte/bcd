package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.RecipeForm;
import com.ucp.cookwithease.model.Comment;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;
import com.ucp.xml.exist.query.QueryCategory;
import com.ucp.xml.exist.query.QuerySimpleUser;

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

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        request.setAttribute("recipe", recipe);
        request.setAttribute("isBookmarked", user.hasBookmark(recipeID));

        return true;
    }

    public boolean addBookmark() {
        recipeID = form.getRecipeIDFromButtons();

        if (form.hasErrors()) {
            return false;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        user.addBookmark(recipeID);

        QuerySimpleUser query = new QuerySimpleUser();
        query.addBookmark(user.getId(), recipeID);
        query.majCat(user.getId(), recipeID, 5);

        return true;
    }

    public boolean addComment() {
        String description = form.getComment();
        int rating = form.getRating();
        recipeID = form.getRecipeIDFromButtons();

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

            QuerySimpleUser query = new QuerySimpleUser();
            query.majCat(user.getId(), recipeID, rating);

            return true;
        }
    }

    public boolean loadSuggestions() {
        recipeID = form.getRecipeIDFromParameter();

        QueryCategory query = new QueryCategory();
        LinkedList<Integer> recipesID = new LinkedList<>(query.findRecipe(recipeID));
        recipesID.remove((Integer) recipeID);

        LinkedList<Recipe> suggestedRecipes = DAOFactory.getRecipeDAO().findAll(recipesID);

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
