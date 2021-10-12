package com.company.MMK.error;

public class ParameterNotFoundException extends Exception {
    private String error;

    public ParameterNotFoundException(String message, String error) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
