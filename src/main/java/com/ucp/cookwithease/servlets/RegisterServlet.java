package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.engine.RegisterPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("userSession") != null) {
            response.sendRedirect(request.getContextPath() + References.VIEW_SEARCH);

        } else {
            this.getServletContext().getRequestDispatcher(
                References.INTERNAL_VIEW_REGISTER).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RegisterPage page = new RegisterPage(request);
        boolean isRegistered = page.registerUser();

        if (isRegistered) {
            response.sendRedirect(request.getContextPath() + References.VIEW_LOGIN);

        } else {
            request.setAttribute("error", page.getFormErrors().getFirst());

            this.getServletContext().getRequestDispatcher(
                References.INTERNAL_VIEW_REGISTER).forward(request, response);
        }
    }
}
