package com.hun.market.member.domain;

import com.hun.market.base.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Table(name = "trans")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CoinTransHistory extends BaseEntity {

    @Id
    @Column(name = "trans_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "trans_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CoinTransType transactionType;

    public static CoinTransHistory createDepositTransaction(Member member, int amount) {
        return CoinTransHistory.builder()
                .member(member)
                .amount(amount)
                .transactionType(CoinTransType.DEPOSIT)
                .build();
    }

    public static CoinTransHistory createWithdrawalTransaction(Member member, int amount) {
        return CoinTransHistory.builder()
                .member(member)
                .amount(amount)
                .transactionType(CoinTransType.WITHDRAWAL)
                .build();
    }
}
