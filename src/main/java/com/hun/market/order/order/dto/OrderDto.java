package com.hun.market.order.order.dto;

import com.hun.market.item.domain.Item;
import com.hun.market.member.domain.Member;
import com.hun.market.order.order.domain.OrderItem;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class OrderDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class OrderCreateRequestDto {

        private List<OrderItemCreateRequestDto> orderItemDtos = new ArrayList<>();
        private String buyer;

    }



    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class OrderItemCreateRequestDto {

        @NotNull(message = "Item  is required")
        private Item item;

        @NotNull(message = "quantity is required")
        @Size(max = 10000, message = "quantity cannot exceed 10000")
        private Integer quantity;

        @NotNull(message = "Item name is required")
        @Size(max = 10000, message = "Price cannot exceed 10000")
        private Long price;


    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class OrderCreateResponseDto {

        private List<Map<Item, Integer>> itemQuantity = new ArrayList<>();

        private Long totalPrice;

        private String description;

    }



}
