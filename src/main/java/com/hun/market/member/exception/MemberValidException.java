package com.hun.market.member.exception;

import lombok.Getter;

@Getter
public class MemberValidException extends RuntimeException {

    private String code = "500";
    private String message;
    public MemberValidException() {
        this.message = "Not Found Item";
    }

    public MemberValidException(String message) {
        this.message = message;
    }
    public MemberValidException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public MemberValidException(Exception e) {
        super(e);
    }
}
