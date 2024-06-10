package com.hun.market.order.order.exception;

import lombok.Getter;

@Getter
public class OrderItemNotValidException extends RuntimeException {

    private String code = "500";
    private String message;
    public OrderItemNotValidException() {
        this.message = "Not Found Item";
    }

    public OrderItemNotValidException(String message) {
        this.message = message;
    }
    public OrderItemNotValidException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public OrderItemNotValidException(Exception e) {
        super(e);
    }
}
