package com.company.MMK.error;

public class UnexpectedErrorException extends Exception {
    private String error;

    public UnexpectedErrorException(String message, String error) {
        super(message);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
