package com.ucp.cookwithease.forms;

import javax.servlet.http.HttpServletRequest;


public class FriendsForm extends Form {
    private static final String ID_DELETE_FRIEND = "delete";
    private static final int ID_MAX_VALUE = 1000000000;

    public FriendsForm(HttpServletRequest request) {
        super(request);
    }

    public int getFriendIDToDelete() {
        int id = getIntValueFrom(getRequest(), ID_DELETE_FRIEND, ID_MAX_VALUE);

        if (id == 0) {
            this.addError(ID_DELETE_FRIEND, "L'identifiant de l'utilisateur est incorrect.");
        }

        return id;
    }
}
