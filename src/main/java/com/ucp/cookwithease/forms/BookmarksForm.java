package com.ucp.cookwithease.forms;

import javax.servlet.http.HttpServletRequest;


public class BookmarksForm extends Form {
    private static final String ID_DELETE_BOOKMARK = "delete";
    private static final int ID_MAX_VALUE = 1000000000;

    public BookmarksForm(HttpServletRequest request) {
        super(request);
    }

    public int getBookmarkIDToDelete() {
        int id = getIntValueFrom(getRequest(), ID_DELETE_BOOKMARK, ID_MAX_VALUE);

        if (id == 0) {
            this.addError(ID_DELETE_BOOKMARK, "L'identifiant de la recette est incorrect.");
        }

        return id;
    }
}
