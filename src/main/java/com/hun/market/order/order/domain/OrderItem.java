package com.hun.market.order.order.domain;

import com.hun.market.item.domain.Item;
import com.hun.market.item.exception.ItemNotFoundException;
import com.hun.market.item.exception.ItemNotValidException;
import com.hun.market.order.order.exception.OrderItemNotValidException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;

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

    @Column(name = "price", nullable = false)
    private Long price;

    public static OrderItem create(Order order, Item item, Integer quantity, Long price) {
        validOrderItem(item, quantity, price);
        return OrderItem.builder()
                        .order(order)
                        .item(item)
                        .quantity(quantity)
                        .price(price)
                        .build();
    }

    private static void validOrderItem(Item item, Integer quantity, Long price) {
        validateItem(item);
        validateQuantity(quantity);
        validatePrice(price);
    }

    private static void validateItem(Item item) {
        if (item == null) {
            throw new ItemNotFoundException("Item is required");
        }
    }

    private static void validateQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0 || quantity > 10000) {
            throw new OrderItemNotValidException("Quantity must be between 1 and 10000");
        }
    }

    private static void validatePrice(Long price) {
        if (price == null || price <= 0 || price > 10000) {
            throw new OrderItemNotValidException("Price must be between 1 and 10000");
        }
    }

    public Long calculateTotalPrice() {
        return price * quantity;
    }

}
