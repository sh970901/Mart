package com.hun.market.order.cart.domain;

import com.hun.market.item.domain.Item;
import com.hun.market.order.cart.dto.CartDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Getter
@Table(name = "cart_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "quantity", nullable = false)
    @Positive
    private Integer quantity;

    public static CartItem createByItem(Item item, Integer quantity) {
        return CartItem.builder()
                .item(item)
                .quantity(quantity)
                .build();
    }

    public void mappingCart(Cart cart) {
        this.cart = cart;
    }

    public void increaseQuantity(Integer quantity){
        this.quantity += quantity;
    }
}
