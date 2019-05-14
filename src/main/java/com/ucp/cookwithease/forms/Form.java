package com.ucp.cookwithease.forms;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;


public abstract class Form {
    private HttpServletRequest request;
    private LinkedList<FieldError> errors;

    public Form(HttpServletRequest request) {
        this.request = request;
        this.errors = new LinkedList<>();
    }

    protected HttpServletRequest getRequest() {
        return request;
    }

    public LinkedList<FieldError> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public void addGlobalError(String message) {
        addError("global", message);
    }

    protected void addError(String field, String message) {
        errors.addLast(new FieldError(field, message));
    }

    protected String getValueFrom(HttpServletRequest request, String fieldName, int maxLength) {
        String inputValue = request.getParameter(fieldName);

        if (inputValue != null &&
            inputValue.length() > 0 &&
            inputValue.length() <= maxLength) {

            return inputValue;
        }

        return null;
    }

    protected int getIntValueFrom(HttpServletRequest request, String fieldName, int maxValue) {
        String inputValue = request.getParameter(fieldName);

        try {
            int result = Integer.parseInt(inputValue);

            if (result <= maxValue) {
                return result;
            }
        } catch (NumberFormatException e) {
            // Nothing
        }

        return 0;
    }
}
