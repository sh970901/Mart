package com.hun.market.member.domain;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_Member");

    MemberRole(String value) {
        this.value = value;
    }

    private String value;
}
