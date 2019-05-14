package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.engine.SearchPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SearchPage page = new SearchPage(request);
        boolean hasSucceeded = page.loadRecipes();

        if (!hasSucceeded) {
            request.setAttribute("error", page.getFormErrors().getFirst());
        }

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_SEARCH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SearchPage page = new SearchPage(request);
        boolean hasSucceeded = page.search();

        if (!hasSucceeded) {
            request.setAttribute("error", page.getFormErrors().getFirst());
        }

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_SEARCH).forward(request, response);
    }
}
