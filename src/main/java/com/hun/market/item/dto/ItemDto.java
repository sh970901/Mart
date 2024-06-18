package com.hun.market.item.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
public class ItemDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class ItemCreateRequestDto {

        @NotNull(message = "Item name is required")
        @Size(max = 100, message = "Item name must be less than 100 characters")
        private String itemName;

        @NotNull(message = "Item stock is required")
        @PositiveOrZero
        private Long itemStock;

        @NotNull(message = "Item price is required")
        @PositiveOrZero
        private Long itemPrice;

        private String description;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class ItemCreatResponseDto {

        @NotNull(message = "Item id is required")
        private Long itemId;

        @NotNull(message = "Item name is required")
        @Size(max = 100, message = "Item name must be less than 100 characters")
        private String itemName;

        @NotNull(message = "Item stock is required")
        @PositiveOrZero
        private Long itemStock;

        @NotNull(message = "Item price is required")
        @PositiveOrZero
        private Long itemPrice;

        private String description;
    }





}
