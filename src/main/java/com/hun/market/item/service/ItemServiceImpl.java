package com.hun.market.item.service;

import com.hun.market.backoffice.dto.ItemModifyDto;
import com.hun.market.item.domain.Item;
import com.hun.market.item.dto.ItemDto;
import com.hun.market.item.exception.ItemNotFoundException;
import com.hun.market.item.repository.ItemRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    /**
     * TODO
     * 레디슨을 활용한 캐싱 처리
     */
    @Validated
//    @Cacheable(cacheNames = "itemListCache", key = "#root.targetClass + '.' + #root.methodName + '.' + #page + '.' + #size", sync = true, cacheManager = "itemCacheManager")
    public List<ItemDto.ItemCreatResponseDto> getItemList(int page, int size) {
        Page<Item> resultItemPage = itemRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));
        log.info("-----------------------good------------------------");
        return resultItemPage.getContent().stream()
                .map(this::mapToItemResponseDto)
                .collect(Collectors.toList());
    }

    @Override public void updateItem(ItemModifyDto itemModifyDto) {

        Optional<Item> optionalItem = itemRepository.findById(itemModifyDto.getItemNo());

        optionalItem.ifPresentOrElse(
            item -> {
                item.updateItem(itemModifyDto);
                itemRepository.save(item);
            },
            () -> {
                throw new ItemNotFoundException("해당 번호의 상품은 존재하지 않습니다." + itemModifyDto.getItemNo());
            }
        );

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

    private ItemDto.ItemCreatResponseDto mapToItemResponseDto(Item item) {
        return ItemDto.ItemCreatResponseDto.builder().itemId(item.getId()).itemName(item.getItemName()).itemPrice(item.getItemPrice()).itemStock(item.getItemStock()).description(item.getDescription()).build();
    }

}
