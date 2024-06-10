package com.hun.market.member.dto;

import com.hun.market.member.domain.Department;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

public class MemberDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class MemberCreateRequestDto {

        @NotNull(message = "member name is required")
        @Size(max = 100, message = "member name must be less than 100 characters")
        private String mbName;

        @NotNull(message = "password is required")
        private String mbPassword;

        @NotNull
        @PositiveOrZero
        private int mbCoin;

        private Department department;

    }

}
