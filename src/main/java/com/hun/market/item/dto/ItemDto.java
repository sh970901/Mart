package com.hun.market.item.dto;

import com.hun.market.item.domain.Item;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public abstract class ItemDto {
    @NotBlank(message = "Item name is required")
    @Size(max = 100, message = "Item name must be less than 100 characters")
    protected String itemName;

    @NotNull(message = "Item stock is required")
    @Min(value = 0, message = "Item stock must be non-negative")
    protected Long itemStock;

    @NotNull(message = "Item price is required")
    @Min(value = 0, message = "Item price must be non-negative")
    protected Long itemPrice;

    protected String description;

    public Item toEntity() {
        return Item.builder()
                .itemName(this.itemName)
                .itemStock(this.itemStock)
                .itemPrice(this.itemPrice)
                .description(this.description)
                .build();
    }
}
