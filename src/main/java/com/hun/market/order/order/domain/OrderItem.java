package com.hun.market.order.order.domain;

import com.hun.market.item.domain.Item;
import com.hun.market.order.claim.domain.Claim;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cache.annotation.EnableCaching;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EnableCaching
@Builder(access = AccessLevel.PRIVATE)
public class OrderItem {
    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @OneToOne(mappedBy = "orderItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Claim claim = null;

    // dto to entity
    public static OrderItem createByItem(Item item, Integer quantity) {
        return OrderItem.builder()
                .item(item)
                .quantity(quantity)
                .build();
    }

    public void mappingOrder(Order order){
        this.order = order;
    }


    public Long calcOrderItemTotalPrice() {
        return this.item.getItemPrice() * quantity;
    }

}
