package com.ucp.cookwithease.engine;

import com.ucp.cookwithease.forms.FieldError;
import com.ucp.cookwithease.forms.Form;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;


public abstract class Page<T> {
    protected T form;
    protected HttpServletRequest request;

    public Page(HttpServletRequest request) {
        this.request = request;
    }

    public LinkedList<FieldError> getFormErrors() {
        return ((Form) form).getErrors();
    }

    public boolean hasErrors() {
        return !((Form) form).hasErrors();
    }
}
