package com.hun.market.order.pay.service;

import com.hun.market.order.order.domain.Order;
import com.hun.market.order.pay.PaymentRepository;
import com.hun.market.order.pay.domain.Payment;
import com.hun.market.order.pay.domain.PaymentStatus;
import com.hun.market.order.pay.dto.PaymentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PaymentCartService implements PaymentService {

    private final PaymentRepository paymentRepository;


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PaymentDto.PaymentResponseDto processPayment(Order order){
        Payment payment = Payment.createByOrder(order);
        PaymentStatus status = payment.process();

        paymentRepository.save(payment);

        boolean success = (status == PaymentStatus.COMPLETED);
        String message = success ? "Payment completed successfully" : "Payment failed";

        return new PaymentDto.PaymentResponseDto(success, message, status);
    }

}
