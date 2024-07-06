package com.hun.market.order.order.repository;


import com.hun.market.order.order.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.lang.annotation.Native;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {


    OrderItem findByItemId(Long id);
}
