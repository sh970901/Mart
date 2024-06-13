package com.hun.market.order.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CartController {

    @GetMapping("/cart")
    public String cartItems(){
        return "cart/cart_items";
    }


}
