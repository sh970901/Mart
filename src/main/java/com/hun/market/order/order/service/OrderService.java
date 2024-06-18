package com.hun.market.order.order.service;

import com.hun.market.core.exception.ResponseServiceException;
import com.hun.market.item.domain.Item;
import com.hun.market.item.exception.ItemNotFoundException;
import com.hun.market.item.exception.ItemStockException;
import com.hun.market.item.repository.ItemRepository;
import com.hun.market.member.domain.Member;
import com.hun.market.member.exception.MemberCoinLackException;
import com.hun.market.member.exception.MemberValidException;
import com.hun.market.member.repository.MemberRepository;
import com.hun.market.order.cart.service.CartService;
import com.hun.market.order.order.domain.Order;
import com.hun.market.order.order.domain.OrderItem;
import com.hun.market.order.order.dto.OrderDto;
import com.hun.market.order.order.dto.OrderDto.OrderItemByCartCreateRequestDto;
import com.hun.market.order.order.repository.OrderRepository;
import com.hun.market.order.pay.service.PaymentService;
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
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final PaymentService paymentCartService;

    private final CartService cartService;

    @Validated
    @Transactional(rollbackFor = Exception.class)
    public OrderDto.OrderCreateResponseDto createOrderByMemberCart(OrderDto.OrderCreateRequestDto orderDto, String buyer) {

        List<OrderItem> orderItems = orderDto2OrderItems(orderDto);

        Member member = memberRepository.findByMbNameWithCart(buyer).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Order order = Order.createByMember(orderItems, member);

        try {
            paymentCartService.processPayment(order);
        }
        catch (ItemStockException | MemberCoinLackException | MemberValidException e){
            log.info("주문 처리 중 예외 발생: " + e.getMessage());
            throw new ResponseServiceException(e.getMessage());
        }


        List<Long> cartItemsIds = orderDto.getOrderItemDtos().stream().map(OrderItemByCartCreateRequestDto::getCartItemId).toList();
        cartService.deleteAllCartItem(cartItemsIds, buyer);


        return OrderDto.OrderCreateResponseDto.builder().description("구매가 완료되었습니다.").build();

    }

    private List<Long> getItemsIds(OrderDto.OrderCreateRequestDto orderDto){

        List<OrderItemByCartCreateRequestDto> orderItemDtos = orderDto.getOrderItemDtos();

        return orderItemDtos.stream()
                            .map(OrderItemByCartCreateRequestDto::getItemId)
                            .toList();

    }

    private List<OrderItem> orderDto2OrderItems(OrderDto.OrderCreateRequestDto orderDto){
        List<Item> items = itemRepository.findAllById(getItemsIds(orderDto));

        List<OrderItem> orderItems = new ArrayList<>();

        // 조회한 Item 엔티티들을 Map으로 변환 (id -> Item)
        Map<Long, Item> itemsByIds = items.stream()
                                          .collect(Collectors.toMap(Item::getId, item -> item));

        // 각 OrderItemCreateRequestDto를 OrderItem으로 변환
        for (OrderItemByCartCreateRequestDto orderItemDto : orderDto.getOrderItemDtos()) {
            Long itemId = orderItemDto.getItemId();

            Item item = Optional.ofNullable(itemsByIds.get(itemId))
                                .orElseThrow(() -> new ItemNotFoundException("상품이 존재하지 않습니다."));

            OrderItem orderItem = OrderItem.createByItem(item, orderItemDto.getQuantity());
            orderItems.add(orderItem);
        }
        return orderItems;
    }

}
