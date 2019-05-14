package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.LoginForm;
import com.ucp.cookwithease.model.User;
import com.ucp.cookwithease.tools.Tools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginPage extends Page<LoginForm> {
    public LoginPage(HttpServletRequest request) {
        super(request);

        form = new LoginForm(request);
    }

    public boolean connectUser() {
        String pseudo = form.getPseudo();
        String password = form.getPassword();

        if (form.hasErrors()) {
            return false;
        }

        User user = DAOFactory.getUserDAO().find(pseudo, Tools.sha256(password));

        if (user == null) {
            form.addGlobalError("L'utilisateur ou le mot de passe est incorrect.");

            return false;

        } else {
            HttpSession session = request.getSession();
            session.setAttribute("userSession", user);

            return true;
        }
    }
}
