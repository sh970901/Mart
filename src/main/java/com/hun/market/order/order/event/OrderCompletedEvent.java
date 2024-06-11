package com.hun.market.order.order.event;

import com.hun.market.order.order.domain.Order;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrderCompletedEvent implements OrderEvent {
    private final Order order;

    public OrderCompletedEvent(Order order) {
        this.order = order;
    }

    @Override public void process() {
      log.info("이벤트 발행");
//        order.complete();
    }

    public Order getOrder() {
        return order;
    }
}
