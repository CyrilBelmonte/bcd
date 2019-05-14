package com.ucp.cookwithease.forms;

import javax.servlet.http.HttpServletRequest;


public class LoginForm extends Form {
    private static final String PSEUDO_FIELD = "pseudo";
    private static final String PASSWORD_FIELD = "password";
    private static final int PSEUDO_LENGTH = 20;
    private static final int PASSWORD_LENGTH = 100;

    public LoginForm(HttpServletRequest request) {
        super(request);
    }

    public String getPseudo() {
        String pseudo = getValueFrom(getRequest(), PSEUDO_FIELD, PSEUDO_LENGTH);

        if (pseudo == null) {
            this.addError(PSEUDO_FIELD, "Un nom d'utilisateur valide est requis.");
        }

        return pseudo;
    }

    public String getPassword() {
        String password = getValueFrom(getRequest(), PASSWORD_FIELD, PASSWORD_LENGTH);

        if (password == null) {
            this.addError(PASSWORD_FIELD, "Un mot de passe valide est requis.");
        }

        return password;
    }
}
