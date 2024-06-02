package com.hun.market.item.exception;

import lombok.Getter;

@Getter
public class ItemNotFoundException extends RuntimeException {

    private String code = "500";
    private String message;
    public ItemNotFoundException() {
        this.message = "Not Found Item";
    }

    public ItemNotFoundException(String message) {
        this.message = message;
    }
    public ItemNotFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ItemNotFoundException(Exception e) {
        super(e);
    }
}
