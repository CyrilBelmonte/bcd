package com.ucp.cookwithease.forms;

import javax.servlet.http.HttpServletRequest;


public class RecipeForm extends Form {
    private static final String ID_PARAMETER = "id";
    private static final String ID_COMMENT_PARAMETER = "add-comment";
    private static final String ID_BOOKMARK_PARAMETER = "add-bookmark";
    private static final String COMMENT_FIELD = "comment";
    private static final String RATING_FIELD = "rating";
    private static final int ID_MAX_VALUE = 1000000000;
    private static final int COMMENT_LENGTH = 200;
    private static final int RATING_MAX_VALUE = 5;

    public RecipeForm(HttpServletRequest request) {
        super(request);
    }

    public String getComment() {
        String comment = getValueFrom(getRequest(), COMMENT_FIELD, COMMENT_LENGTH);

        if (comment == null) {
            this.addError(COMMENT_FIELD, "Le commentaire est incorrect.");
        }

        return comment;
    }

    public int getRating() {
        int rating = getIntValueFrom(getRequest(), RATING_FIELD, RATING_MAX_VALUE);

        if (rating == 0) {
            this.addError(RATING_FIELD, "La note attribuée est incorrecte.");
        }

        return rating;
    }

    public int getRecipeIDFromButtons() {
        int idBookmark = getIntValueFrom(getRequest(), ID_COMMENT_PARAMETER, ID_MAX_VALUE);
        int idComment = getIntValueFrom(getRequest(), ID_BOOKMARK_PARAMETER, ID_MAX_VALUE);
        int id = Math.max(idBookmark, idComment);

        if (id == 0) {
            this.addError(ID_PARAMETER, "L'identifiant de la recette est incorrect.");
        }

        return id;
    }

    public int getRecipeIDFromParameter() {
        int id = getIntValueFrom(getRequest(), ID_PARAMETER, ID_MAX_VALUE);

        if (id == 0) {
            this.addError(ID_PARAMETER, "L'identifiant de la recette est incorrect.");
        }

        return id;
    }
}
