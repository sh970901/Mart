package com.hun.market.item.event;

import com.hun.market.order.order.event.OrderCompletedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class ItemEventListener {

    @EventListener
    public void handleOrderCompletedEvent(DecreaseItStockEvent event) {
        event.process();
    }
}
