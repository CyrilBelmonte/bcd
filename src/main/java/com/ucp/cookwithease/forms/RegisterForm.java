package com.ucp.cookwithease.forms;

import com.ucp.cookwithease.tools.Tools;

import javax.servlet.http.HttpServletRequest;


public class RegisterForm extends Form {
    private static final String FIRST_NAME_FIELD = "first-name";
    private static final String LAST_NAME_FIELD = "last-name";
    private static final String PSEUDO_FIELD = "pseudo";
    private static final String EMAIL_FIELD = "email";
    private static final String PASSWORD_FIELD = "password";
    private static final String PASSWORD_CONFIRM_FIELD = "password-confirmation";
    private static final int FIRST_NAME_LENGTH = 50;
    private static final int LAST_NAME_LENGTH = 50;
    private static final int PSEUDO_LENGTH = 20;
    private static final int EMAIL_LENGTH = 50;
    private static final int PASSWORD_LENGTH = 100;

    public RegisterForm(HttpServletRequest request) {
        super(request);
    }

    public String getFirstName() {
        String firstName = getValueFrom(getRequest(), FIRST_NAME_FIELD, FIRST_NAME_LENGTH);

        if (firstName == null) {
            this.addError(FIRST_NAME_FIELD, "Un prénom valide est requis.");
        }

        return firstName;
    }

    public String getLastName() {
        String lastName = getValueFrom(getRequest(), LAST_NAME_FIELD, LAST_NAME_LENGTH);

        if (lastName == null) {
            this.addError(LAST_NAME_FIELD, "Un nom valide est requis.");
        }

        return lastName;
    }

    public String getPseudo() {
        String pseudo = getValueFrom(getRequest(), PSEUDO_FIELD, PSEUDO_LENGTH);

        if (pseudo == null) {
            this.addError(PSEUDO_FIELD, "Un nom d'utilisateur valide est requis.");
        }

        return pseudo;
    }

    public String getEmail() {
        String email = getValueFrom(getRequest(), EMAIL_FIELD, EMAIL_LENGTH);

        if (!Tools.isEmail(email)) {
            this.addError(EMAIL_FIELD, "Une adresse email valide est requise.");

            return null;
        }

        return email;
    }

    public String getPassword() {
        String password = getValueFrom(getRequest(), PASSWORD_FIELD, PASSWORD_LENGTH);
        String passwordConfirmation = getValueFrom(getRequest(), PASSWORD_CONFIRM_FIELD, PASSWORD_LENGTH);

        if (password == null || password.length() < 4) {
            this.addError(PASSWORD_FIELD, "Un mot de passe valide est requis.");

            return null;
        }

        if (!password.equals(passwordConfirmation)) {
            this.addError(PASSWORD_CONFIRM_FIELD, "Les deux mots de passe ne sont pas identiques.");

            return null;
        }

        return password;
    }
}
