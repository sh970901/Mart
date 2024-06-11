package com.hun.market.order.pay.service;

import com.hun.market.order.order.domain.Order;
import com.hun.market.order.pay.dto.PaymentDto;
import org.springframework.stereotype.Service;


public interface PaymentService {
    PaymentDto.PaymentResponseDto processPayment(Order order);

}
