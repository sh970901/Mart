package com.hun.market.order.pay;

import com.hun.market.order.pay.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
