package com.hun.market.order.pay.dto;

import com.hun.market.order.pay.domain.PaymentStatus;
import lombok.*;

@Getter
public class PaymentDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @Builder
    public static class PaymentResponseDto{
        private boolean success;
        private String message;
        private PaymentStatus status;
    }
}
