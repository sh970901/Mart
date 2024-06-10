package com.hun.market.order.order.service;

import com.hun.market.order.order.dto.OrderDto;
import com.hun.market.order.order.dto.OrderDto.OrderCreateRequestDto;
import com.hun.market.order.order.dto.OrderDto.OrderItemCreateRequestDto;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional(readOnly = true)
public class OrderService {

    @Validated
    @Transactional
    public OrderDto.OrderCreateResponseDto createOrder(OrderCreateRequestDto orderDto) {



//        Long totalPrice = orderItemDtos.stream()
//                    .mapToLong(OrderDto.OrderItemCreateRequestDto::getPrice)
//                    .sum();



        return null;

    }
}
