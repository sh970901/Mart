package com.hun.market.item.service;

import com.hun.market.item.domain.Item;
import com.hun.market.item.dto.ItemResponseDto;
import com.hun.market.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    public List<ItemResponseDto> getItemList(int page, int size) {
        Page<Item> resultItemPage = itemRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
        return resultItemPage.getContent().stream()
                .map(this::mapToItemResponseDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public Page<ItemResponseDto> paging(Pageable pageable) {
//        int page = pageable.getPageNumber(); // page 위치에 있는 값은 0부터 시작한다.
//        int pageLimit = pageable.getPageSize(); // 한페이지에 보여줄 글 개수
//
//        Page<Item> itemsPages = itemRepository.findAll(PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.ASC, "id")));
//
//        return itemsPages.map(
//                this::mapToItemResponseDto);
//    }

    private ItemResponseDto mapToItemResponseDto(Item item) {
        return ItemResponseDto.from().itemName(item.getItemName()).itemPrice(item.getItemPrice()).itemStock(item.getItemStock()).description(item.getDescription()).build();
    }

}
