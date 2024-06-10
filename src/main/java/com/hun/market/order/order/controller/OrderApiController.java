package com.hun.market.order.order.controller;

import com.hun.market.member.domain.MemberContext;
import com.hun.market.order.order.dto.OrderDto;
import com.hun.market.order.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderApiController {

    private final OrderService orderService;

    @PostMapping("/orders/create")
    public OrderDto.OrderCreateResponseDto createOrder(@Valid @RequestBody OrderDto.OrderCreateRequestDto orderDto, @AuthenticationPrincipal MemberContext memberDto){

        String buyer = memberDto.getUsername();
        return orderService.createOrderByMember(orderDto, buyer);
    }

}
