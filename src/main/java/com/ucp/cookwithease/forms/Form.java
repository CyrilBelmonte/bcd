package com.ucp.cookwithease.forms;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;


public abstract class Form {
    private LinkedList<FieldError> errors = new LinkedList<>();

    public LinkedList<FieldError> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    protected String getValueFrom(HttpServletRequest request, String fieldName, int maxLength) {
        String value = request.getParameter(fieldName);

        if (value != null &&
            value.length() > 0 &&
            value.length() <= maxLength) {

            return value;
        }

        return null;
    }

    protected void addError(String field, String message) {
        errors.addLast(new FieldError(field, message));
    }
}
