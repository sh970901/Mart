package com.hun.market.order.cart.domain;

import com.hun.market.member.domain.Member;
import com.hun.market.order.order.domain.Order;
import com.hun.market.order.order.domain.OrderStatus;
import com.hun.market.order.ship.domain.ShippingAddress;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    public static Cart createByMember(CartItem cartItem, Member member) {
        Cart cart = Cart.builder()
                .member(member)
                .cartItems(List.of(cartItem))
                .build();

        cartItem.mappingCart(cart);

        return cart;
    }
}
