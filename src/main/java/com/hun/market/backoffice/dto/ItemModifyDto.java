package com.hun.market.backoffice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ItemModifyDto {

    @NotNull(message = "상품번호는 필수값 입니다.")
    private Long itemNo;

    @NotNull(message = "상품명은 필수값 입니다.")
    @Size(max = 100, message = "Item name must be less than 100 characters")
    private String itemName;

    @NotNull(message = "상품 가격은 필수값 입니다.(0원 이상)")
    @PositiveOrZero
    private Long itemPrice;

    @NotNull(message = "상품 재고는 필수값 입니다.(0개 이상)")
    @PositiveOrZero
    private Long itemStock;

    private String description;

}


