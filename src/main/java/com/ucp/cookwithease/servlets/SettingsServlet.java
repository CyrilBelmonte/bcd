package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.engine.SettingsPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


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

        SettingsPage page = new SettingsPage(request);
        boolean hasSucceeded = false;

        if (request.getParameter("apply-user-settings") != null) {
            hasSucceeded = page.updateUser();

            request.setAttribute("formName", "user-settings");

        } else if (request.getParameter("apply-password") != null) {
            hasSucceeded = page.updateUserPassword();

            request.setAttribute("formName", "password");
        }

        if (!hasSucceeded) {
            request.setAttribute("error", page.getFormErrors().getFirst());
        }

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_SETTINGS).forward(request, response);
    }
}
