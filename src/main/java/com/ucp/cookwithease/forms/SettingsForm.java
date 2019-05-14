package com.ucp.cookwithease.forms;

import com.ucp.cookwithease.tools.Tools;

import javax.servlet.http.HttpServletRequest;


public class SettingsForm extends Form {
    private static final String FIRST_NAME_FIELD = "first-name";
    private static final String LAST_NAME_FIELD = "last-name";
    private static final String EMAIL_FIELD = "email";
    private static final String CURRENT_PASSWORD_FIELD = "current-password";
    private static final String PASSWORD_FIELD = "password";
    private static final String PASSWORD_CONFIRM_FIELD = "password-confirmation";
    private static final int FIRST_NAME_LENGTH = 50;
    private static final int LAST_NAME_LENGTH = 50;
    private static final int EMAIL_LENGTH = 50;
    private static final int PASSWORD_LENGTH = 100;

    public SettingsForm(HttpServletRequest request) {
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

    public String getEmail() {
        String email = getValueFrom(getRequest(), EMAIL_FIELD, EMAIL_LENGTH);

        if (!Tools.isEmail(email)) {
            this.addError(EMAIL_FIELD, "Une adresse email valide est requise.");

            return null;
        }

        return email;
    }

    public String getCurrentPassword() {
        String currentPassword = getValueFrom(getRequest(), CURRENT_PASSWORD_FIELD, PASSWORD_LENGTH);

        if (currentPassword == null) {
            this.addError(CURRENT_PASSWORD_FIELD, "Le mot de passe actuel est incorrect.");

            return null;
        }

        return currentPassword;
    }

    public String getNewPassword() {
        String password = getValueFrom(getRequest(), PASSWORD_FIELD, PASSWORD_LENGTH);
        String passwordConfirmation = getValueFrom(getRequest(), PASSWORD_CONFIRM_FIELD, PASSWORD_LENGTH);

        if (password == null || password.length() < 4) {
            this.addError(PASSWORD_FIELD, "Le nouveau mot de passe est incorrect.");

            return null;
        }

        if (!password.equals(passwordConfirmation)) {
            this.addError(PASSWORD_CONFIRM_FIELD, "Les deux mots de passe ne sont pas identiques.");

            return null;
        }

        return password;
    }
}
