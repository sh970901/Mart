package com.hun.market.item.domain;

import com.hun.market.base.entity.BaseEntity;
import com.hun.market.item.dto.ItemDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "item")
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 protected로 설정
@AllArgsConstructor
public class Item extends BaseEntity {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="item_name", nullable = false)
    private String itemName;

    @Column(name="item_stock", nullable = false)
    private Long itemStock;

    @Column(name="item_price", nullable = false)
    private Long itemPrice;

    @Column(name="item_description")
    private String description;

    @Builder
    public Item(String itemName, Long itemPrice, Long itemStock, String description) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemStock = itemStock;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                '}';
    }

    public static Item from(ItemDto.ItemCreateRequestDto itemDto){
        return Item.builder()
                    .itemName(itemDto.getItemName())
                    .itemStock(itemDto.getItemStock())
                    .itemPrice(itemDto.getItemPrice())
                    .description(itemDto.getDescription())
                    .build();
    }



}
