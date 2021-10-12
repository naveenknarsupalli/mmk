package com.company.MMK.error;

import java.util.List;

public class ParameterMissingException extends Exception {
    private List<String> errors;

    public ParameterMissingException(String message, List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
