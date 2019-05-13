package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.forms.FieldError;
import com.ucp.cookwithease.forms.RecipeForm;
import com.ucp.cookwithease.forms.SettingsForm;
import com.ucp.cookwithease.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;


public class RecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RecipeForm form = new RecipeForm();

        Recipe recipe = form.getRecipe(request);

        if (form.hasErrors()) {
            response.sendRedirect(request.getContextPath() + References.VIEW_SEARCH);

        } else {
            request.setAttribute("recipe", recipe);

            this.getServletContext().getRequestDispatcher(
                References.INTERNAL_VIEW_RECIPE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RecipeForm form = new RecipeForm();
        LinkedList<FieldError> errors = form.getErrors();

        if (request.getParameter("add-bookmark") != null) {
            form.addBookmark(request);
            request.setAttribute("formName", "bookmark");

            response.sendRedirect(request.getContextPath() + References.VIEW_BOOKMARKS);

        } else if (request.getParameter("add-comment") != null) {
            form.addComment(request);
            request.setAttribute("formName", "comment");

            if (form.hasErrors()) {
                request.setAttribute("error", errors.getFirst());
            }

            response.sendRedirect(request.getContextPath() + References.VIEW_RECIPE + "?id=" + form.getRecipeID());
        }
    }
}
