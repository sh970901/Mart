package com.hun.market.order.order.event;

import com.hun.market.order.order.domain.Order;

public class OrderCompletedEvent implements OrderEvent {
    private final Order order;

    public OrderCompletedEvent(Order order) {
        this.order = order;
    }

    @Override public void process() {
        order.complete();
    }

    public Order getOrder() {
        return order;
    }
}
