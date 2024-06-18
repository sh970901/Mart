package com.hun.market.item.event;

import com.hun.market.item.domain.Item;
import com.hun.market.order.order.domain.OrderItem;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class DecreaseItStockEvent implements ItemEvent {

    List<OrderItem> orderItems = new ArrayList<>();
    public DecreaseItStockEvent(List<OrderItem> orderItems){
        this.orderItems = orderItems;
    }


    @Override public void process() {

        for (OrderItem orderItem : orderItems){
            int quantity = orderItem.getQuantity();
            orderItem.getItem().decreaseStock(quantity);
        }

    }
}
