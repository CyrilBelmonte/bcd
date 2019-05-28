package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.engine.DiscoverPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class DiscoverServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DiscoverPage page = new DiscoverPage(request);

        page.loadStarters();
        request.setAttribute("type", "starters");

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_DISCOVER).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DiscoverPage page = new DiscoverPage(request);

        if (request.getParameter("starters") != null) {
            page.loadStarters();
            request.setAttribute("type", "starters");

        } else if (request.getParameter("main-courses") != null) {
            page.loadMainCourses();
            request.setAttribute("type", "mainCourses");

        } else if (request.getParameter("desserts") != null) {
            page.loadDesserts();
            request.setAttribute("type", "desserts");
        }

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_DISCOVER).forward(request, response);
    }
}
