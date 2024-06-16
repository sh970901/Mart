package com.hun.market.item.service;

import com.hun.market.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto.ItemCreatResponseDto> getItemList(int page, int size);

}
