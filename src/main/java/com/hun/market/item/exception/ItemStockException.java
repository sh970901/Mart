package com.hun.market.item.exception;

import lombok.Getter;

@Getter
public class ItemStockException extends RuntimeException {

    private String code = "500";
    private String message;
    public ItemStockException() {
        this.message = "Not Found Item";
    }

    public ItemStockException(String message) {
        this.message = message;
    }
    public ItemStockException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ItemStockException(Exception e) {
        super(e);
    }
}
