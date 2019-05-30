package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.dao.DAOFactory;
import com.ucp.cookwithease.forms.FriendsForm;
import com.ucp.cookwithease.model.User;
import com.ucp.xml.exist.query.QuerySimpleUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.LinkedList;


public class FriendsPage extends Page<FriendsForm> {
    public FriendsPage(HttpServletRequest request) {
        super(request);

        form = new FriendsForm(request);
    }

    public boolean loadFriends() {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        LinkedList<Integer> friendsID = user.getFriends();
        LinkedList<User> friends = DAOFactory.getUserDAO().findAll(friendsID);

        if (friends.size() == 0) {
            form.addGlobalError("Nouez des liens avec des passionnés de cuisine !");

            return false;

        } else {
            request.setAttribute("friends", friends);

            return true;
        }
    }

    public boolean deleteFriend() {
        int friendID = form.getFriendIDToDelete();

        if (form.hasErrors()) {
            return false;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");
        user.deleteFriend(friendID);

        QuerySimpleUser query = new QuerySimpleUser();
        query.deleteFriend(user.getId(), friendID);

        return true;
    }
}
