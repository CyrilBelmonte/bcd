package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.forms.FieldError;
import com.ucp.cookwithease.forms.SearchForm;
import com.ucp.cookwithease.model.Recipe;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;


public class SearchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SearchForm form = new SearchForm();

        LinkedList<Recipe> recipes = form.searchAll();
        request.setAttribute("recipes", recipes);

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_SEARCH).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SearchForm form = new SearchForm();
        LinkedList<FieldError> errors = form.getErrors();

        LinkedList<Recipe> recipes = form.search(request);
        request.setAttribute("recipes", recipes);

        if (form.hasErrors()) {
            request.setAttribute("error", errors.getFirst());
        }

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_SEARCH).forward(request, response);
    }
}
