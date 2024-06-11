package com.hun.market.order.order.event;

import com.hun.market.order.order.domain.Order;
import org.springframework.context.event.EventListener;

public class OrderEventListener {
    @EventListener
    public void handleOrderCompletedEvent(OrderCompletedEvent event) {
        // 주문 완료 이벤트를 처리하는 로직을 여기에 구현
        // 주문 정보를 이용하여 다양한 작업을 수행
        event.process();
        // 주문 완료 메일 발송, 알림 등의 작업을 수행
    }
}
