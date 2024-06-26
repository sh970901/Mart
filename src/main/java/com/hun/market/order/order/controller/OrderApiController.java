package com.hun.market.order.order.controller;

import com.hun.market.member.domain.MemberContext;
import com.hun.market.order.order.dto.OrderDto;
import com.hun.market.order.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/o")
@RequiredArgsConstructor
@Slf4j
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/order/create")
    public OrderDto.OrderCreateResponseDto createOrderByCart(@Valid @RequestBody OrderDto.OrderCreateRequestDto orderDto, @AuthenticationPrincipal MemberContext memberDto){
        String buyer = memberDto.getUsername();
        log.info("buyer: {}", buyer);

        return orderService.createOrderByMemberCart(orderDto, buyer);
    }

    @PostMapping("/order/create/item")
    public OrderDto.OrderCreateResponseDto createOrder(@Valid @RequestBody OrderDto.OrderItemCreateRequestDto orderItemDto, @AuthenticationPrincipal MemberContext memberDto){
        String buyer = memberDto.getUsername();
        log.info("buyer: {}", buyer);

        return orderService.createOrderByMember(orderItemDto, buyer);
    }



}
