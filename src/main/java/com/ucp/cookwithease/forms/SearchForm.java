package com.ucp.cookwithease.forms;

import javax.servlet.http.HttpServletRequest;


public class SearchForm extends Form {
    private static final String SEARCH_FIELD = "search";
    private static final int SEARCH_LENGTH = 100;

    public SearchForm(HttpServletRequest request) {
        super(request);
    }

    public String getSearchedKeywords() {
        return getValueFrom(getRequest(), SEARCH_FIELD, SEARCH_LENGTH);
    }
}
