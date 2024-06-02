package com.hun.market.item.service;

import com.hun.market.item.dto.ItemResponseDto;

import java.util.List;

public interface ItemService {
    List<ItemResponseDto> getItemList(int page, int size);
}
