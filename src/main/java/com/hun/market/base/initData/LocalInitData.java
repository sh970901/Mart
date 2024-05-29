package com.hun.market.base.initData;

import com.hun.market.item.domain.Item;
import com.hun.market.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("local")
public class LocalInitData extends AbstractInitData {

    private boolean initDataDone = false;
    private final ItemRepository itemRepository;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            if (initDataDone) return;
            Item item = new Item();
            item.setItemName("aa");
            itemRepository.save(item);
            before();

            initDataDone = true;
        };
    }
}
