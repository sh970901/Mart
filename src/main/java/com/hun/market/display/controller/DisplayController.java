package com.hun.market.display.controller;

import com.hun.market.item.domain.Item;
import com.hun.market.item.dto.ItemResponseDto;
import com.hun.market.item.repository.ItemRepository;
import com.hun.market.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class DisplayController {

    private final ItemService itemService;

    @GetMapping("/")
    public String display(@RequestParam(defaultValue = "0") int page,
                          @RequestParam(defaultValue = "18") int size, Model model){

        List<ItemResponseDto> itemList = itemService.getItemList(page, size);

        for(ItemResponseDto item: itemList ){
            log.info(item.getItemName());
            log.info(item.getDescription());
            log.info(String.valueOf(item.getItemPrice()));
            log.info(String.valueOf(item.getItemStock()));
        }
        model.addAttribute("itemList", itemList);


        return "display/home";
    }
}
