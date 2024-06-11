package com.hun.market.order.pay.domain;

import com.hun.market.base.entity.BaseEntity;
import com.hun.market.core.event.Events;
import com.hun.market.order.order.domain.Order;
import com.hun.market.order.order.event.OrderCompletedEvent;
import com.hun.market.order.pay.dto.PaymentDto.PaymentResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

        // 주문 상태 완료 발행
        Events.raise(new OrderCompletedEvent(order));
        // 회원 코인 차감 발행
        // 상품 재고 차감 발행
        return null;
    }
}
