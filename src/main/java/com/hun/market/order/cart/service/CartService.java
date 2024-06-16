package com.hun.market.order.cart.service;

import com.hun.market.order.cart.dto.CartDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CartService {
    CartDto.CartCreateResponseDto addCartItemByMember(CartDto.CartItemCreateRequestDto cartItemDto, String member);

    List<CartDto.CartItemCreateResponseDto> getCartItemList(Pageable pageable, String member);

    CartDto.CartItemDeleteResponseDto deleteCartItem(Long cartItemId, String username);
}
