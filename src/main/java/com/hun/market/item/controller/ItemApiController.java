package com.hun.market.item.controller;

import com.hun.market.item.dto.ItemDto;
import com.hun.market.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/i")
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    @GetMapping("/items")
    public List<ItemDto.ItemCreatResponseDto> getItems(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "18") int size) {
        return itemService.getItemList(page, size);
    }
}
