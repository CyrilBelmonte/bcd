package com.ucp.cookwithease.forms;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.User;
import com.ucp.cookwithease.tools.Tools;

import javax.servlet.http.HttpServletRequest;


public class LoginForm extends Form {
    private static final String PSEUDO_FIELD = "pseudo";
    private static final int PSEUDO_LENGTH = 20;
    private static final String PASSWORD_FIELD = "password";
    private static final int PASSWORD_LENGTH = 100;

    public User getUser(HttpServletRequest request) {
        String pseudo = getValueFrom(request, PSEUDO_FIELD, PSEUDO_LENGTH);
        String password = getValueFrom(request, PASSWORD_FIELD, PASSWORD_LENGTH);

        if (pseudo == null)
            this.addError(PSEUDO_FIELD, "Un nom d'utilisateur valide est requis.");

        if (password == null)
            this.addError(PASSWORD_FIELD, "Un mot de passe valide est requis.");

        if (this.hasErrors())
            return null;

        User user = DAOFactory.getUserDAO().find(pseudo, Tools.sha256(password));

        if (user == null)
            this.addError(null, "L'utilisateur ou le mot de passe est incorrect.");

        return user;
    }
}
