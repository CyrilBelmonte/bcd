package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.engine.ProfilePage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProfilePage page = new ProfilePage(request);
        boolean isLoaded = page.loadProfile();

        if (!isLoaded) {
            response.sendRedirect(request.getContextPath() + References.VIEW_SEARCH);

        } else {
            this.getServletContext().getRequestDispatcher(
                References.INTERNAL_VIEW_PROFILE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProfilePage page = new ProfilePage(request);
        page.follow();

        response.sendRedirect(request.getContextPath() + References.VIEW_PROFILE + "?id=" + page.getProfileID());
    }
}
