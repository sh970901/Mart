package com.hun.market.item.controller;

import com.hun.market.item.dto.ItemDto;
import com.hun.market.item.dto.ItemDto.ItemSearchDto;
import com.hun.market.item.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/i")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items")
    public String searchItems(@RequestParam("q") String query, @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "18") int size, Model model) {
        // 검색 로직 구현
        List<ItemDto.ItemCreatResponseDto> searchResults = itemService.getSearchItemList(query, page, size);
        System.out.println(searchResults.size());
        model.addAttribute("itemList", searchResults);
        model.addAttribute("keyword", query);
        return "item/search_items"; // 검색 결과를 표시할 뷰 이름
    }


}
