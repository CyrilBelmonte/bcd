package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.forms.LoginForm;
import com.ucp.cookwithease.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("userSession") != null)
            response.sendRedirect(request.getContextPath() + References.VIEW_SEARCH);

        else
            this.getServletContext().getRequestDispatcher(
                    References.INTERNAL_VIEW_LOGIN).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        LoginForm form = new LoginForm();
        User user = form.getUser(request);

        if (!form.hasErrors()) {
            session.setAttribute("userSession", user);
            response.sendRedirect(request.getContextPath() + References.VIEW_SEARCH);
        }

        else {
            this.getServletContext().getRequestDispatcher(
                    References.INTERNAL_VIEW_LOGIN).forward(request, response);
        }
    }
}
