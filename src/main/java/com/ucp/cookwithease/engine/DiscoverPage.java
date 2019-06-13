package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.forms.DiscoverForm;
import com.ucp.cookwithease.model.DiscoverSuggestions;
import com.ucp.cookwithease.model.DishType;
import com.ucp.cookwithease.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class DiscoverPage extends Page<DiscoverForm> {
    public DiscoverPage(HttpServletRequest request) {
        super(request);

        form = new DiscoverForm(request);
    }

    public boolean loadStarters() {
        return loadSuggestions(DishType.STARTER);
    }

    public boolean loadMainCourses() {
        return loadSuggestions(DishType.MAIN_COURSE);
    }

    public boolean loadDesserts() {
        return loadSuggestions(DishType.DESSERT);
    }

    private boolean loadSuggestions(DishType type) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userSession");

        DiscoverSuggestions discoverSuggestions = SuggestionEngine.getDiscoverSuggestion(user, type);
        request.setAttribute("suggestions", discoverSuggestions);

        return true;
    }
}
