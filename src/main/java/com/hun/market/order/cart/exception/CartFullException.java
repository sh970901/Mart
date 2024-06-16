package com.hun.market.order.cart.exception;

import lombok.Getter;

@Getter
public class CartFullException extends RuntimeException {

    private String code = "500";
    private String message;
    public CartFullException() {
        this.message = "Not Found Item";
    }

    public CartFullException(String message) {
        this.message = message;
    }
    public CartFullException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public CartFullException(Exception e) {
        super(e);
    }
}
