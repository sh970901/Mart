package com.hun.market.order.cart.domain;

import com.hun.market.member.domain.Member;
import com.hun.market.order.cart.exception.CartFullException;
import com.hun.market.order.order.domain.Order;
import com.hun.market.order.order.domain.OrderStatus;
import com.hun.market.order.ship.domain.ShippingAddress;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@Table(name = "carts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder(access = AccessLevel.PRIVATE)
public class Cart {

    @Id
    @Column(name = "cart_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public static Cart createByMember(CartItem cartItem, Member member) {
        Cart cart = Cart.builder()
                .member(member)
                .cartItems(List.of(cartItem))
                .build();

        cartItem.mappingCart(cart);
        member.mappingCart(cart);

        return cart;
    }

    public Cart addCartItem(CartItem cartItem){
        validCartSize();

        // 카트에 이미 존재하는지 확인
        Optional<CartItem> existingCartItem = findCartItemByItemId(cartItem.getItem().getId());
        if (existingCartItem.isPresent()) {
            // 이미 존재하는 경우 수량 증가
            existingCartItem.get().increaseQuantity(cartItem.getQuantity());
        } else {
            // 새로운 아이템을 카트에 추가
            this.cartItems.add(cartItem);
        }
        cartItem.mappingCart(this);

        return this;
    }

    // 아이템 ID로 카트 아이템 찾기
    private Optional<CartItem> findCartItemByItemId(Long itemId) {
        return this.cartItems.stream()
                .filter(cartItem -> cartItem.getItem().getId().equals(itemId))
                .findFirst();
    }

    private void validCartSize() {
        if (cartItems.size() >= 15){
            throw new CartFullException("Cart is over 15 cart items");
        }
    }

    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
    }

    public Cart addCartItem(CartItem cartItem, Member cartMember) {
        if (cartMember.isExistCart()){
            return addCartItem(cartItem);
        }
        else {
            return createByMember(cartItem, cartMember);
        }
    }

//    private Cart createByMember(CartItem cartItem, Member member) {
//
//        this.member = member;
//        member.mappingCart(this);
//
//        this.cartItems.add(cartItem);
//        cartItem.mappingCart(this);
//
//        return this;
//    }

}
