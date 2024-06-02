package com.hun.market.item.dto;

import com.hun.market.item.domain.Item;
import jakarta.validation.constraints.*;
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
