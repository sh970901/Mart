package com.hun.market.base.initData;

import com.hun.market.item.dto.ItemCreationDto;
import com.hun.market.item.dto.ItemDto;
import com.hun.market.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("local")
public class InitDataForLocal extends AbstractInitData {

    private boolean initDataDone = false;
    private final ItemRepository itemRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            if (initDataDone) return;
            for(int i = 0; i<100; i++){
                ItemDto itemDto = ItemCreationDto.from().itemName("item"+i).itemPrice(2000L).itemStock(3L).description(i+"번 상품입니다.").build();
                itemRepository.save(itemDto.toEntity());
            }
            before();

            initDataDone = true;
        };
    }
}
