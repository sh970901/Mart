package com.hun.market.base.initData;

import com.hun.market.item.domain.Item;
import com.hun.market.item.dto.ItemDto;
import com.hun.market.item.repository.ItemRepository;
import com.hun.market.member.domain.Department;
import com.hun.market.member.domain.Member;
import com.hun.market.member.dto.MemberDto;
import com.hun.market.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("local")
public class InitDataForLocal extends AbstractInitData {

    private boolean initDataDone = false;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initData() {
        return args -> {
            if (initDataDone) return;
            before();
            for(int i = 0; i<100; i++){
                ItemDto.ItemCreateRequestDto itemDto = ItemDto.ItemCreateRequestDto.builder().itemName("item"+i).itemPrice(2000L).itemStock(3L).description(i+"번 상품입니다.").build();
                Item item = Item.from(itemDto);
                itemRepository.save(item);
            }
            String password = passwordEncoder.encode("1234");
            Department department = Department.builder().departmentName("1").teamName("1").position("1").build();
            MemberDto.MemberCreateRequestDto mbDto = MemberDto.MemberCreateRequestDto.builder().mbName("admin").mbPassword(password).mbCoin(1000).department(department).build();
            memberRepository.save(Member.from(mbDto));

            MemberDto.MemberCreateRequestDto mbDto2= MemberDto.MemberCreateRequestDto.builder().mbName("admin2").mbPassword(password).mbCoin(12000).department(department).build();
            memberRepository.save(Member.from(mbDto2));


            initDataDone = true;
        };
    }
}
