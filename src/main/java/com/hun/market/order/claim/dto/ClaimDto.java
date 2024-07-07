package com.hun.market.order.claim.dto;

import com.hun.market.member.domain.Member;
import com.hun.market.order.order.domain.OrderItem;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Getter
public class ClaimDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class ClaimCreateRequestDto {

        @NotNull(message = "amount  is required")
        private Long refundAmount;

        @NotNull(message = "time  is required")
        private LocalDateTime time;

        @NotNull(message = "ItemName  is required")
        private OrderItem orderItem;

        private Member member;

    }
}
