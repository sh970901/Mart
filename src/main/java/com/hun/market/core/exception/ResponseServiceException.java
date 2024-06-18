package com.hun.market.core.exception;

public class ResponseServiceException extends RuntimeException {
    private final String message;

    public ResponseServiceException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}