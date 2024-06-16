package com.hun.market.order.cart.service;

import com.hun.market.item.domain.Item;
import com.hun.market.item.exception.ItemNotFoundException;
import com.hun.market.item.repository.ItemRepository;
import com.hun.market.member.domain.Member;
import com.hun.market.member.repository.MemberRepository;
import com.hun.market.order.cart.domain.Cart;
import com.hun.market.order.cart.domain.CartItem;
import com.hun.market.order.cart.dto.CartDto;
import com.hun.market.order.cart.repository.CartRepository;
import com.hun.market.order.order.domain.Order;
import com.hun.market.order.order.domain.OrderItem;
import com.hun.market.order.order.dto.OrderDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CartServiceImpl implements CartService{

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    @Transactional
    @Validated
    @Override
    public CartDto.CartCreateResponseDto addCartItemByMember(@Valid CartDto.CartItemCreateRequestDto cartItemDto, String member) {

        Member cartMember = memberRepository.findByMbNameWithCart(member).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        /**
         * 카트가 있다면 기존 카트 아이템 리스트에 추가
         * 카트가 없다면 카트 생성
         */
        CartItem cartItem = cartItemDto2CartItem(cartItemDto);

        Cart cart = cartMember.getCart();
        if (cart == null) {
            cart = Cart.createByMember(cartItem, cartMember);
        } else {
            cart.addCartItem(cartItem);
        }

        cartRepository.save(cart);

        return null;
    }

    private CartItem cartItemDto2CartItem(CartDto.CartItemCreateRequestDto cartItemDto) {
        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(() -> new ItemNotFoundException("사용자를 찾을 수 없습니다."));

        return CartItem.createByItem(item, cartItemDto.getQuantity());
    }

}
