package com.hun.market.order.order.controller;

import com.hun.market.member.domain.MemberContext;
import com.hun.market.member.repository.MemberRepository;
import com.hun.market.order.order.dto.OrderDto;
import com.hun.market.order.order.dto.OrderDto.OrderItemCreateRequestDto;
import com.hun.market.order.order.dto.OrderDto.OrderItemResponseDto;
import com.hun.market.order.order.service.OrderService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
@RequiredArgsConstructor
public class OrderApiController {

    private final MemberRepository memberRepository;
    private final OrderService orderService;

    @PostMapping("/orders/create")
    public OrderDto.OrderCreateResponseDto createOrder(@Valid  @RequestBody List<OrderDto.OrderItemCreateRequestDto> orderItemDtos, @AuthenticationPrincipal MemberContext memberDto){

        OrderDto.OrderCreateRequestDto orderDto = OrderDto.OrderCreateRequestDto.builder()
                                                                                .orderItemDtos(orderItemDtos)
                                                                                .buyer(memberDto.getMbName())
                                                                                .build();

        return orderService.createOrder(orderDto);
    }
}
