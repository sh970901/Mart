package com.hun.market.item.service;

import com.hun.market.item.domain.Item;
import com.hun.market.item.dto.ItemDto;
import com.hun.market.item.dto.ItemDto.ItemCreatResponseDto;
import com.hun.market.item.repository.ItemRepository;
import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class WrapperItemService {
    private final ItemRepository itemRepository;

    @Cacheable(cacheNames = "itemListCache", key = "#root.methodName + '.' + #page + '.' + #size", sync = true, cacheManager = "itemCacheManager")
    public WrapperItemResponseDtos getItemList(int page, int size) {
        log.info("-----------------------DB find------------------------");

        Page<Item> resultItemPage = itemRepository.findAll(PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));

        List<ItemCreatResponseDto> itemCreatResponseDtos = resultItemPage.getContent().stream()
                                                                         .map(ItemCreatResponseDto::of)
                                                                         .toList();

        return WrapperItemResponseDtos.builder().itemCreatResponseDtos(itemCreatResponseDtos).build();
    }

    @Validated
//    @Cacheable(cacheNames = "searchItemListCache", key = "#root.methodName + '.' + #page + '.' + #size", sync = true, cacheManager = "itemCacheManager")
    public WrapperItemResponseDtos getSearchItemList(String query, int page, int size) {
        log.info("-----------------------search------------------------");

        Page<Item> resultItemPage = itemRepository.findByItemNameContaining(query, PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id")));

        List<ItemCreatResponseDto> itemCreatResponseDtos = resultItemPage.getContent().stream()
                                                                         .map(ItemCreatResponseDto::of)
                                                                         .toList();

        return WrapperItemResponseDtos.builder().itemCreatResponseDtos(itemCreatResponseDtos).build();
    }


    @Data
    @NoArgsConstructor
    public static class WrapperItemResponseDtos{
        private List<ItemDto.ItemCreatResponseDto> itemCreatResponseDtos = new ArrayList<>();

        @Builder
        public WrapperItemResponseDtos(List<ItemDto.ItemCreatResponseDto> itemCreatResponseDtos){
            this.itemCreatResponseDtos = itemCreatResponseDtos;
        }
    }
}
