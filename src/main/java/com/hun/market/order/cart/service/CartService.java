package com.hun.market.order.cart.service;

import com.hun.market.order.cart.dto.CartDto;

public interface CartService {
    CartDto.CartCreateResponseDto addCartItemByMember(CartDto.CartItemCreateRequestDto cartItemDto, String member);
}
