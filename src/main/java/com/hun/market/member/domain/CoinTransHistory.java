package com.hun.market.member.domain;

import com.hun.market.backoffice.dto.CoinProvideRequestDto;
import com.hun.market.base.entity.BaseEntity;
import jakarta.persistence.*;
import java.time.LocalDate;
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

    // Todo 엔티티 생성일과 별개로 코인 지급 사유 발생일 저장(재고는 엔티티 생성일 기준 으로 맞추 도록 할까요)
    //  * 지급같은 경우에는 이벤트 생성일 별도 지정
    //  * 주문같은 경우는 엔티티 생성일 = 이벤트 생성일
    @Column(name = "event_date")
    private LocalDate eventDate;

    @Column(name = "description")
    private String description;




    public static CoinTransHistory createDepositTransaction(Member member, CoinProvideRequestDto coinProvideRequestDto) {
        return CoinTransHistory.builder()
                               .member(member)
                               .amount(coinProvideRequestDto.getCoin())
                               .transactionType(coinProvideRequestDto.getCoinTransType())
                               .eventDate(coinProvideRequestDto.getPaymentDate())
                               .description(coinProvideRequestDto.getDescription())
                               .build();
    }

    // Todo 주문정보를 받아서 처리
    public static CoinTransHistory createWithdrawalTransaction(Member member, int amount) {
        return CoinTransHistory.builder()
                .member(member)
                .amount(amount)
                .transactionType(CoinTransType.WITHDRAWAL)
                .description("주문")
                .build();
    }


}
