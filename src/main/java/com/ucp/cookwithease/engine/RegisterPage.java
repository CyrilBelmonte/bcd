package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.RegisterForm;
import com.ucp.cookwithease.model.User;
import com.ucp.cookwithease.tools.Tools;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;


public class RegisterPage extends Page<RegisterForm> {
    public RegisterPage(HttpServletRequest request) {
        super(request);

        form = new RegisterForm(request);
    }

    public boolean registerUser() {
        String firstName = form.getFirstName();
        String lastName = form.getLastName();
        String pseudo = form.getPseudo();
        String email = form.getEmail();
        String password = form.getPassword();

        if (form.hasErrors()) {
            return false;
        }

        boolean alreadyUsedPseudo = DAOFactory.getUserDAO().find(pseudo) != null;

        if (alreadyUsedPseudo) {
            form.addGlobalError("Le nom d'utilisateur est déjà utilisé.");

            return false;
        }

        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPseudo(pseudo);
        user.setEmail(email);
        user.setPassword(Tools.sha256(password));
        user.setInscriptionDate(new Date());

        boolean hasSucceeded = DAOFactory.getUserDAO().insert(user);

        if (!hasSucceeded) {
            form.addGlobalError("Une erreur inconnue s'est produite.");

            return false;

        } else {
            return true;
        }
    }
}
