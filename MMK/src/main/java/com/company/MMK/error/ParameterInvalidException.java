package com.company.MMK.error;

import java.util.List;

public class ParameterInvalidException extends Exception {
    private List<String> errors;

    public ParameterInvalidException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
