package com.ucp.cookwithease.forms;

import javax.servlet.http.HttpServletRequest;


public class ProfileForm extends Form {
    private static final String ID_PARAMETER = "id";
    private static final String ID_FOLLOW_PARAMETER = "follow";
    private static final int ID_MAX_VALUE = 1000000000;

    public ProfileForm(HttpServletRequest request) {
        super(request);
    }

    public int getProfileIDFromFollow() {
        int id = getIntValueFrom(getRequest(), ID_FOLLOW_PARAMETER, ID_MAX_VALUE);

        if (id == 0) {
            this.addError(ID_PARAMETER, "L'identifiant de l'utilisateur est incorrect.");
        }

        return id;
    }

    public int getProfileIDFromParameter() {
        int id = getIntValueFrom(getRequest(), ID_PARAMETER, ID_MAX_VALUE);

        if (id == 0) {
            this.addError(ID_PARAMETER, "L'identifiant de l'utilisateur est incorrect.");
        }

        return id;
    }
}
