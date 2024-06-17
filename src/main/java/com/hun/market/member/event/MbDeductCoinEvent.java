package com.hun.market.member.event;

import com.hun.market.member.domain.Member;

public class MbDeductCoinEvent implements MemberEvent {

    private Member member;
    private int payCoin;

    public MbDeductCoinEvent(Member member){
        this.member = member;
    }

    public MbDeductCoinEvent(Member buyer, int totalPrice) {
        this.member = buyer;
        this.payCoin = totalPrice;
    }

    @Override public void process() {
        member.deductCoin(payCoin);
    }
}
