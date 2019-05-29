package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.engine.AssistantPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AssistantServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AssistantPage page = new AssistantPage(request);
        boolean isLoaded = page.loadMenu();

        if (!isLoaded) {
            request.setAttribute("error", page.getFormErrors().getFirst());
        }

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_ASSISTANT).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        super.doPost(request, response);
    }
}
