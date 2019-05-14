package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.SettingsForm;
import com.ucp.cookwithease.model.User;
import com.ucp.cookwithease.tools.Tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SettingsPage extends Page<SettingsForm> {
    public SettingsPage(HttpServletRequest request) {
        super(request);

        form = new SettingsForm(request);
    }

    public boolean updateUser() {
        String firstName = form.getFirstName();
        String lastName = form.getLastName();
        String email = form.getEmail();

        if (form.hasErrors()) {
            return false;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        boolean hasSucceeded = DAOFactory.getUserDAO().update(user);

        if (!hasSucceeded) {
            form.addGlobalError("Une erreur inconnue s'est produite.");

            return false;

        } else {
            return true;
        }
    }

    public boolean updateUserPassword() {
        String currentPassword = form.getCurrentPassword();
        String newPassword = form.getNewPassword();

        if (form.hasErrors()) {
            return false;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        boolean isCurrentPasswordValid = DAOFactory.getUserDAO().find(
            user.getPseudo(),
            Tools.sha256(currentPassword)) != null;

        if (!isCurrentPasswordValid) {
            form.addGlobalError("Le mot de passe actuel est incorrect.");

            return false;
        }

        user.setPassword(Tools.sha256(newPassword));
        boolean hasSucceeded = DAOFactory.getUserDAO().updatePassword(user);
        user.setPassword(null);

        if (!hasSucceeded) {
            form.addGlobalError("Une erreur inconnue s'est produite.");

            return false;

        } else {
            return true;
        }
    }
}
