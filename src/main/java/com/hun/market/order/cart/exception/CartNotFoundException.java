package com.hun.market.order.cart.exception;

import lombok.Getter;

@Getter
public class CartNotFoundException extends RuntimeException {

    private String code = "500";
    private String message;
    public CartNotFoundException() {
        this.message = "Not Found Item";
    }

    public CartNotFoundException(String message) {
        this.message = message;
    }
    public CartNotFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CartNotFoundException(Exception e) {
        super(e);
    }
}
