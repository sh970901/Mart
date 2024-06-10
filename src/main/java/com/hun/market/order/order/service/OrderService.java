package com.hun.market.order.order.service;

import com.hun.market.item.domain.Item;
import com.hun.market.item.exception.ItemNotFoundException;
import com.hun.market.item.repository.ItemRepository;
import com.hun.market.member.domain.Member;
import com.hun.market.member.repository.MemberRepository;
import com.hun.market.order.order.domain.Order;
import com.hun.market.order.order.domain.OrderItem;
import com.hun.market.order.order.dto.OrderDto;
import com.hun.market.order.order.dto.OrderDto.OrderItemCreateRequestDto;
import com.hun.market.order.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;

    @Validated
    @Transactional
    public OrderDto.OrderCreateResponseDto createOrderByMember(OrderDto.OrderCreateRequestDto orderDto, String buyer) {

        List<OrderItemCreateRequestDto> orderItemDtos = orderDto.getOrderItemDtos();

        /**
         * 하나씩 쿼리 vs 한 방 쿼리
         */

        List<OrderItem> orderItems = new ArrayList<>();

//        for(OrderItemCreateRequestDto orderItemDto : orderItemDtos) {
//            Item item = itemRepository.findById(orderItemDto.getItemId()).orElseThrow(()-> new ItemNotFoundException("상품이 없습니다."));
//            OrderItem orderItem = OrderItem.createByItem(item, orderItemDto.getQuantity());
//            orderItems.add(orderItem);
//        }

        // OrderItemCreateRequestDto에서 itemId 리스트를 추출
        List<Long> itemIds = orderItemDtos.stream()
                .map(OrderItemCreateRequestDto::getItemId)
                .collect(Collectors.toList());

        // itemId 리스트를 사용하여 한 번에 모든 Item 엔티티를 조회
        List<Item> items = itemRepository.findAllById(itemIds);

        // 조회한 Item 엔티티들을 Map으로 변환 (id -> Item)
        Map<Long, Item> itemMap = items.stream()
                .collect(Collectors.toMap(Item::getId, item -> item));

        // 각 OrderItemCreateRequestDto를 OrderItem으로 변환
        for (OrderItemCreateRequestDto orderItemDto : orderItemDtos) {
            Long itemId = orderItemDto.getItemId();
            Item item = itemMap.get(itemId);
            if (item == null) {
                throw new ItemNotFoundException("상품이 존재하지 않습니다.");
            }
            OrderItem orderItem = OrderItem.createByItem(item, orderItemDto.getQuantity());
            orderItems.add(orderItem);
        }

        Member member = memberRepository.findByMbName(buyer).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        Order order = Order.createByMember(orderItems, member);
        orderRepository.save(order);


        return null;

    }
}
