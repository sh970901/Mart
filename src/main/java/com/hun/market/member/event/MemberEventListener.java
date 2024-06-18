package com.hun.market.member.event;

import com.hun.market.item.event.DecreaseItStockEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class MemberEventListener {

    @EventListener
    public void handleOrderCompletedEvent(MbDeductCoinEvent event) {
        event.process();
    }
}
