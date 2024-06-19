package com.hun.market.item.service;

import com.hun.market.backoffice.dto.ItemModifyDto;
import com.hun.market.item.dto.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto.ItemCreatResponseDto> getItemList(int page, int size);

    void updateItem(ItemModifyDto itemModifyDto);
}
