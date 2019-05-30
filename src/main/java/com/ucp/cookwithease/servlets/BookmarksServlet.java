package com.ucp.cookwithease.servlets;

import com.ucp.cookwithease.engine.BookmarksPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class BookmarksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookmarksPage page = new BookmarksPage(request);
        boolean hasSucceeded = page.loadBookmarks();

        if (!hasSucceeded) {
            request.setAttribute("error", page.getFormErrors().getFirst());
        }

        this.getServletContext().getRequestDispatcher(
            References.INTERNAL_VIEW_BOOKMARKS).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookmarksPage page = new BookmarksPage(request);
        page.deleteBookmark();

        response.sendRedirect(request.getContextPath() + References.VIEW_BOOKMARKS);
    }
}
