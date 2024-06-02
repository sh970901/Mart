package com.hun.market.item.dto;

import jakarta.annotation.Nullable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ItemResponseDto extends ItemDto{

    public ItemResponseDto(){
        super();
    }

    @Builder(builderMethodName = "from")
    private ItemResponseDto(String itemName, Long itemStock, Long itemPrice, String description) {
        super.itemName = itemName;
        super.itemStock = itemStock;
        super.itemPrice = itemPrice;
        super.description = description;
    }

}
