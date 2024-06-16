package com.hun.market.order.cart.service;

import com.hun.market.item.domain.Item;
import com.hun.market.item.exception.ItemNotFoundException;
import com.hun.market.item.repository.ItemRepository;
import com.hun.market.member.domain.Member;
import com.hun.market.member.exception.MemberNotMatchException;
import com.hun.market.member.repository.MemberRepository;
import com.hun.market.order.cart.domain.Cart;
import com.hun.market.order.cart.domain.CartItem;
import com.hun.market.order.cart.dto.CartDto;
import com.hun.market.order.cart.exception.CartFullException;
import com.hun.market.order.cart.exception.CartItemNotFoundException;
import com.hun.market.order.cart.exception.CartNotFoundException;
import com.hun.market.order.cart.repository.CartItemRepository;
import com.hun.market.order.cart.repository.CartRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class CartServiceImpl implements CartService {

    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
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

        Cart cart = null;

        try {
             cart = cartMember.addCartItem(cartItem);
        }
        catch (CartFullException e){
            return CartDto.CartCreateResponseDto.builder().description("찜목록의 최대 갯수는 15개 입니다.").build();
        }
//        Cart cart = cartMember.getCart();

//        try {
//            if (cart == null){
//
//            }
//            else {
//
//            }
//            cart = cart.addCartItem(cartItem, cartMember);
//        }
//        cart.addCartItem(cartItem, cartMember);
//
//        try {
//            if (cart == null) {
//                cart = Cart.createByMember(cartItem, cartMember);
//            } else {
//                cart.addCartItem(cartItem);
//            }
//        } catch (CartFullException e){
//            return CartDto.CartCreateResponseDto.builder().description("찜목록의 최대 갯수는 15개 입니다.").build();
//        }


        cartRepository.save(cart);

        return CartDto.CartCreateResponseDto.builder().description("찜목록에 추가되었습니다.").build();
    }

    @Override
    public List<CartDto.CartItemCreateResponseDto> getCartItemList(Pageable pageable, String member) {
        Member cartMember = memberRepository.findByMbNameWithCart(member).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Cart cart = cartRepository.findByMember(cartMember).orElse(null);
        if (cart == null) return null;

        Page<CartItem> cartItemsPage = cartItemRepository.findByCart(cart, pageable);
        if (cartItemsPage.getContent().isEmpty()) return null;


        return cartItemsPage.stream()
                .map(cartItem -> CartDto.CartItemCreateResponseDto.builder().cartItemId(cartItem.getId()).itemId(cartItem.getItem().getId()).itemName(cartItem.getItem().getItemName()).quantity(cartItem.getQuantity()).build())
                .toList();
    }

    @Transactional
    @Override
    public CartDto.CartItemDeleteResponseDto deleteCartItem(Long cartItemId, String username) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new CartItemNotFoundException("CartItem not found"));

        if (!cartItem.getCart().getMember().getMbName().equals(username)) {
            throw new MemberNotMatchException("Unauthorized action");
        }

        cartItem.decreaseQuantity();

        if (cartItem.getCart() == null){
            return decreaseResponse("찜목록이 취소되었습니다.");
        }
        else {
            return decreaseResponse("갯수가 차감되었습니다.");
        }
    }

    private CartItem cartItemDto2CartItem(CartDto.CartItemCreateRequestDto cartItemDto) {
        Item item = itemRepository.findById(cartItemDto.getItemId()).orElseThrow(() -> new ItemNotFoundException("사용자를 찾을 수 없습니다."));

        return CartItem.createByItem(item, cartItemDto.getQuantity());
    }
    private CartDto.CartItemDeleteResponseDto decreaseResponse(String description) {
        return CartDto.CartItemDeleteResponseDto.builder().description(description).build();
    }


}
