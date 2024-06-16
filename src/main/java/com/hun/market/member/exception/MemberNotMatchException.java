package com.hun.market.member.exception;

import lombok.Getter;

@Getter
public class MemberNotMatchException extends RuntimeException {

    private String code = "500";
    private String message;
    public MemberNotMatchException() {
        this.message = "Not Found Item";
    }

    public MemberNotMatchException(String message) {
        this.message = message;
    }
    public MemberNotMatchException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public MemberNotMatchException(Exception e) {
        super(e);
    }
}
