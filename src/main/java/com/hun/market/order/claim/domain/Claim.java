package com.hun.market.order.claim.domain;

import com.hun.market.base.entity.BaseEntity;
import com.hun.market.member.domain.Member;
import com.hun.market.order.order.domain.OrderItem;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "claims")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Claim extends BaseEntity {

    @Id
    @Column(name = "claim_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ClaimStatus status;

    @Column(name = "refund_amount", nullable = false)
    private Long refundAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_item_id", nullable = false)
    private OrderItem orderItem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

}
