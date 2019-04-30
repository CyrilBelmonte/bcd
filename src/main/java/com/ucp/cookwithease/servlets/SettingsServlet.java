package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.forms.FieldError;
import com.ucp.cookwithease.forms.SettingsForm;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;


public class SettingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_SETTINGS).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SettingsForm form = new SettingsForm();
        LinkedList<FieldError> errors = form.getErrors();

        if (request.getParameter("apply-user-settings") != null) {
            form.updateUser(request);
            request.setAttribute("formName", "user-settings");

        } else if (request.getParameter("apply-password") != null) {
            form.updateUserPassword(request);
            request.setAttribute("formName", "password");
        }

        if (form.hasErrors()) {
            request.setAttribute("error", errors.getFirst());
        }

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_SETTINGS).forward(request, response);
    }
}
