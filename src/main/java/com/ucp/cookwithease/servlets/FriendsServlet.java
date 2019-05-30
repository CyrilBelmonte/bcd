package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.engine.FriendsPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class FriendsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FriendsPage page = new FriendsPage(request);
        boolean hasSucceeded = page.loadFriends();

        if (!hasSucceeded) {
            request.setAttribute("error", page.getFormErrors().getFirst());
        }

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_FRIENDS).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        FriendsPage page = new FriendsPage(request);
        page.deleteFriend();

        response.sendRedirect(request.getContextPath() + References.VIEW_FRIENDS);
    }
}
