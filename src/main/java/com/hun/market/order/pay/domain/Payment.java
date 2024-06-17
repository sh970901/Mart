package com.hun.market.order.pay.domain;

import com.hun.market.base.entity.BaseEntity;
import com.hun.market.core.event.Events;
import com.hun.market.order.order.domain.Order;
import com.hun.market.order.order.event.OrderCompletedEvent;
import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Entity
@Getter
@Table(name = "payments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Payment extends BaseEntity {
    @Id
    @Column(name = "payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;
    @Column(name = "payment_price", nullable = false)
    @PositiveOrZero
    private Long amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    public static Payment createByOrder(Order order){
                return Payment.builder()
                    .order(order)
                    .amount(order.getTotalPrice())
                    .status(PaymentStatus.PENDING)
                    .build();
    }

    public PaymentStatus process() {


        Events.raise(new OrderCompletedEvent(order));

        return null;
    }
}
