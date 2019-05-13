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

    protected void addError(String field, String message) {
        errors.addLast(new FieldError(field, message));
    }
}
