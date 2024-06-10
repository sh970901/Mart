package com.hun.market.item.exception;

import lombok.Getter;

@Getter
public class ItemNotValidException extends RuntimeException {

    private String code = "500";
    private String message;
    public ItemNotValidException() {
        this.message = "Not Found Item";
    }

    public ItemNotValidException(String message) {
        this.message = message;
    }
    public ItemNotValidException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ItemNotValidException(Exception e) {
        super(e);
    }
}
