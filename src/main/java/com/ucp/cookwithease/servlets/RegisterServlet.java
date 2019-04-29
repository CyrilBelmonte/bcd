package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.forms.FieldError;
import com.ucp.cookwithease.forms.RegisterForm;
import com.ucp.cookwithease.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;


public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("userSession") != null) {
            response.sendRedirect(request.getContextPath() + References.VIEW_SEARCH);

        } else {
            this.getServletContext().getRequestDispatcher(
                References.INTERNAL_VIEW_REGISTER).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RegisterForm form = new RegisterForm();
        LinkedList<FieldError> errors = form.getErrors();
        User user = form.registerUser(request);

        if (!form.hasErrors()) {
            response.sendRedirect(request.getContextPath() + References.VIEW_LOGIN);

        } else {
            request.setAttribute("requestedUser", user);
            request.setAttribute("error", errors.getFirst());

            this.getServletContext().getRequestDispatcher(
                References.INTERNAL_VIEW_REGISTER).forward(request, response);
        }
    }
}
