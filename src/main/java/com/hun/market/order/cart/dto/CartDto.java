package com.hun.market.order.cart.dto;

import com.hun.market.item.domain.Item;
import com.hun.market.order.order.dto.OrderDto;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class CartDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class CartItemCreateRequestDto {

        @NotNull(message = "Item  is required")
        private Long itemId;

        @NotNull(message = "quantity is required")
        private int quantity;

    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class CartCreateResponseDto {

        private String description;

    }
}
