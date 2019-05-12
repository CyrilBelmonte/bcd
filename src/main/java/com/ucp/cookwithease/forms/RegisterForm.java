package com.ucp.cookwithease.forms;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.model.User;
import com.ucp.cookwithease.tools.Tools;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public class RegisterForm extends Form {
    private static final String FIRST_NAME_FIELD = "first-name";
    private static final int FIRST_NAME_LENGTH = 50;
    private static final String LAST_NAME_FIELD = "last-name";
    private static final int LAST_NAME_LENGTH = 50;
    private static final String PSEUDO_FIELD = "pseudo";
    private static final int PSEUDO_LENGTH = 20;
    private static final String EMAIL_FIELD = "email";
    private static final int EMAIL_LENGTH = 50;
    private static final String PASSWORD_FIELD = "password";
    private static final int PASSWORD_LENGTH = 100;
    private static final String PASSWORD_CONFIRM_FIELD = "password-confirmation";
    private static final int PASSWORD_CONFIRM_LENGTH = 100;

    public User registerUser(HttpServletRequest request) {
        String firstName = getValueFrom(request, FIRST_NAME_FIELD, FIRST_NAME_LENGTH);
        String lastName = getValueFrom(request, LAST_NAME_FIELD, LAST_NAME_LENGTH);
        String pseudo = getValueFrom(request, PSEUDO_FIELD, PSEUDO_LENGTH);
        String email = getValueFrom(request, EMAIL_FIELD, EMAIL_LENGTH);
        String password = getValueFrom(request, PASSWORD_FIELD, PASSWORD_LENGTH);
        String passwordConfirmation = getValueFrom(request, PASSWORD_CONFIRM_FIELD, PASSWORD_CONFIRM_LENGTH);

        if (firstName == null) {
            this.addError(FIRST_NAME_FIELD, "Un prénom valide est requis.");
        }

        if (lastName == null) {
            this.addError(LAST_NAME_FIELD, "Un nom valide est requis.");
        }

        if (pseudo == null) {
            this.addError(PSEUDO_FIELD, "Un nom d'utilisateur valide est requis.");
        }

        if (!Tools.isEmail(email)) {
            this.addError(EMAIL_FIELD, "Une adresse email valide est requise.");
        }

        if (password == null || password.length() < 4) {
            this.addError(PASSWORD_FIELD, "Un mot de passe valide est requis.");
        }

        if (password == null || !password.equals(passwordConfirmation)) {
            this.addError(PASSWORD_CONFIRM_FIELD, "Les deux mots de passe ne sont pas identiques.");
        }

        if (this.hasErrors()) {
            return null;
        }

        boolean alreadyUsedPseudo = DAOFactory.getUserDAO().find(pseudo) != null;

        if (alreadyUsedPseudo) {
            this.addError("global", "Le nom d'utilisateur est déjà utilisé.");
            return null;
        }

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPseudo(pseudo);
        user.setEmail(email);
        user.setPassword(Tools.sha256(password));
        user.setInscriptionDate(new Date());

        DAOFactory.getUserDAO().insert(user);

        return user;
    }
}
