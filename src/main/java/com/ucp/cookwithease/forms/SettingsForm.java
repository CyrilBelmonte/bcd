package com.ucp.cookwithease.forms;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.User;
import com.ucp.cookwithease.tools.Tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SettingsForm extends Form {
    private static final String FIRST_NAME_FIELD = "first-name";
    private static final int FIRST_NAME_LENGTH = 50;
    private static final String LAST_NAME_FIELD = "last-name";
    private static final int LAST_NAME_LENGTH = 50;
    private static final String EMAIL_FIELD = "email";
    private static final int EMAIL_LENGTH = 50;

    private static final String CURRENT_PASSWORD_FIELD = "current-password";
    private static final int CURRENT_PASSWORD_LENGTH = 100;
    private static final String PASSWORD_FIELD = "password";
    private static final int PASSWORD_LENGTH = 100;
    private static final String PASSWORD_CONFIRM_FIELD = "password-confirmation";
    private static final int PASSWORD_CONFIRM_LENGTH = 100;

    public boolean updateUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        String firstName = getValueFrom(request, FIRST_NAME_FIELD, FIRST_NAME_LENGTH);
        String lastName = getValueFrom(request, LAST_NAME_FIELD, LAST_NAME_LENGTH);
        String email = getValueFrom(request, EMAIL_FIELD, EMAIL_LENGTH);

        if (firstName == null) {
            this.addError(FIRST_NAME_FIELD, "Un prénom valide est requis.");
        }

        if (lastName == null) {
            this.addError(LAST_NAME_FIELD, "Un nom valide est requis.");
        }

        if (!Tools.isEmail(email)) {
            this.addError(EMAIL_FIELD, "Une adresse email valide est requise.");
        }

        if (this.hasErrors()) {
            return false;
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        return DAOFactory.getUserDAO().update(user);
    }

    public boolean updateUserPassword(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        String currentPassword = getValueFrom(request, CURRENT_PASSWORD_FIELD, CURRENT_PASSWORD_LENGTH);
        String password = getValueFrom(request, PASSWORD_FIELD, PASSWORD_LENGTH);
        String passwordConfirmation = getValueFrom(request, PASSWORD_CONFIRM_FIELD, PASSWORD_CONFIRM_LENGTH);

        if (currentPassword == null || DAOFactory.getUserDAO().find(user.getPseudo(), Tools.sha256(currentPassword)) == null) {
            this.addError(CURRENT_PASSWORD_FIELD, "Le mot de passe actuel n'est pas valide.");
        }

        if (password == null || password.length() < 4) {
            this.addError(PASSWORD_FIELD, "Le nouveau mot de passe n'est pas valide.");
        }

        if (password == null || !password.equals(passwordConfirmation)) {
            this.addError(PASSWORD_CONFIRM_FIELD, "Les deux mots de passe ne sont pas identiques.");
        }

        if (this.hasErrors()) {
            return false;
        }

        user.setPassword(Tools.sha256(password));
        boolean hasSucceeded = DAOFactory.getUserDAO().updatePassword(user);
        user.setPassword(null);

        return hasSucceeded;
    }
}
