package com.hun.market.member.event;

import com.hun.market.member.domain.Member;

public class MbCoinHistoryEvent implements MemberEvent {

    private Member member;

    public MbCoinHistoryEvent(Member member){
        this.member = member;
    }

    public MbCoinHistoryEvent(Member buyer, int totalPrice) {
        this.member = buyer;
    }

    @Override public void process() {
        
    }
}
