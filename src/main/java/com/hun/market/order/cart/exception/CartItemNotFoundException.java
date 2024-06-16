package com.hun.market.order.cart.exception;

import lombok.Getter;

@Getter
public class CartItemNotFoundException extends RuntimeException {

    private String code = "500";
    private String message;
    public CartItemNotFoundException() {
        this.message = "Not Found Item";
    }

    public CartItemNotFoundException(String message) {
        this.message = message;
    }
    public CartItemNotFoundException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CartItemNotFoundException(Exception e) {
        super(e);
    }
}
