package com.hun.market.order.cart.repository;

import com.hun.market.order.cart.domain.Cart;
import com.hun.market.order.cart.domain.CartItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Page<CartItem> findByCart(Cart cart, Pageable pageable);
}
