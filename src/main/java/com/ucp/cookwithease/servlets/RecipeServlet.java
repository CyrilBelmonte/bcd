package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.engine.RecipePage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class RecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RecipePage page = new RecipePage(request);
        boolean isLoaded = page.loadRecipe();

        if (!isLoaded) {
            response.sendRedirect(request.getContextPath() + References.VIEW_SEARCH);

        } else {
            this.getServletContext().getRequestDispatcher(
                References.INTERNAL_VIEW_RECIPE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RecipePage page = new RecipePage(request);
        boolean hasSucceeded;

        if (request.getParameter("add-bookmark") != null) {
            page.addBookmark();

            request.setAttribute("formName", "bookmark");

            response.sendRedirect(request.getContextPath() + References.VIEW_BOOKMARKS);

        } else if (request.getParameter("add-comment") != null) {
            hasSucceeded = page.addComment();

            request.setAttribute("formName", "comment");

            if (!hasSucceeded) {
                request.setAttribute("error", page.getFormErrors().getFirst());
            }

            response.sendRedirect(request.getContextPath() + References.VIEW_RECIPE + "?id=" + page.getRecipeID());
        }
    }
}
