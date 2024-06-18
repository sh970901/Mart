package com.hun.market.member.exception;

import lombok.Getter;

@Getter
public class MemberCoinLackException extends RuntimeException {

    private String code = "500";
    private String message;
    public MemberCoinLackException() {
        this.message = "Not Found Item";
    }

    public MemberCoinLackException(String message) {
        this.message = message;
    }
    public MemberCoinLackException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public MemberCoinLackException(Exception e) {
        super(e);
    }
}
