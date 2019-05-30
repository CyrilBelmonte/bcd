package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.BookmarksForm;
import com.ucp.cookwithease.model.Recipe;
import com.ucp.cookwithease.model.User;
import com.ucp.xml.exist.query.QuerySimpleUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;


public class BookmarksPage extends Page<BookmarksForm> {
    public BookmarksPage(HttpServletRequest request) {
        super(request);

        form = new BookmarksForm(request);
    }

    public boolean loadBookmarks() {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        LinkedList<Integer> bookmarksID = user.getBookmarks();
        LinkedList<Recipe> bookmarks = DAOFactory.getRecipeDAO().findAll(bookmarksID);

        if (bookmarks.size() == 0) {
            form.addGlobalError("Ajoutez dès maintenant vos recettes préférées !");

            return false;

        } else {
            request.setAttribute("bookmarks", bookmarks);

            return true;
        }
    }

    public boolean deleteBookmark() {
        int bookmarkID = form.getBookmarkIDToDelete();

        if (form.hasErrors()) {
            return false;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        user.deleteBookmark(bookmarkID);

        QuerySimpleUser query = new QuerySimpleUser();
        query.deleteBookmark(user.getId(), bookmarkID);

        return true;
    }
}
