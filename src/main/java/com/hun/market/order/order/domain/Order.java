package com.hun.market.order.order.domain;

import com.hun.market.base.entity.BaseEntity;
import com.hun.market.member.domain.Member;
import com.hun.market.order.order.dto.OrderDto;
import com.hun.market.order.order.dto.OrderDto.OrderItemCreateRequestDto;
import com.hun.market.order.ship.domain.ShippingAddress;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.EAGER;
import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Order extends BaseEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member buyer;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "shipping_street"))
    })
    private ShippingAddress shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();


    public static Order create(List<OrderItemCreateRequestDto> orderItemCreateRequestDtos, Member buyer) {
        Order order = Order.builder()
                           .buyer(buyer)
                           .totalPrice(0L)
                           .build();

        for (OrderItemCreateRequestDto dto : orderItemCreateRequestDtos) {
            OrderItem.create(order, dto.getItem(), dto.getQuantity(), dto.getPrice());
        }

        order.calculateTotalPrice();
        return order;
    }

    public void calculateTotalPrice() {
        this.totalPrice = orderItems.stream()
                                    .mapToLong(OrderItem::calculateTotalPrice)
                                    .sum();
    }

    @Override
    public String toString() {
        return "Order{" +
            "id=" + id +
            ", buyer='" + buyer + '\'' +
            ", orderStatus='" + orderStatus + '\'' +
            ", totalPrice=" + totalPrice +
            ", shippingAddress=" + shippingAddress +
            ", orderItems=" + orderItems +
            '}';
    }
}
