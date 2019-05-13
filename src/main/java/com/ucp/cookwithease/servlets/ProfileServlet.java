package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.forms.ProfileForm;
import com.ucp.cookwithease.forms.RecipeForm;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProfileForm form = new ProfileForm();

        User profile = form.getProfile(request);

        if (form.hasErrors()) {
            response.sendRedirect(request.getContextPath() + References.VIEW_SEARCH);

        } else {
            request.setAttribute("profile", profile);

            this.getServletContext().getRequestDispatcher(
                References.INTERNAL_VIEW_PROFILE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ProfileForm form = new ProfileForm();
        form.follow(request);

        response.sendRedirect(request.getContextPath() + References.VIEW_BOOKMARKS);
    }
}
