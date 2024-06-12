package com.hun.market.order.pay.service;

import com.hun.market.order.order.domain.Order;
import com.hun.market.order.pay.dto.PaymentDto;


public interface PaymentService {
    PaymentDto.PaymentResponseDto processPayment(Order order);

}
