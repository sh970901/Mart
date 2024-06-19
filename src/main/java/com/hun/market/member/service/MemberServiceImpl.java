package com.hun.market.member.service;

import com.hun.market.backoffice.dto.CoinProvideRequestDto;
import com.hun.market.item.domain.Item;
import com.hun.market.item.exception.ItemNotFoundException;
import com.hun.market.member.domain.Member;
import com.hun.market.member.repository.MemberRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void provideCoin(CoinProvideRequestDto coinProvideRequestDto) {

        int provideCoin = coinProvideRequestDto.getCoin();

        for (Long memberId : coinProvideRequestDto.getEmployeeList()) {

            Optional<Member> optionalMember = memberRepository.findById(memberId);

            optionalMember.ifPresentOrElse(
                member -> {
                    member.provideCoin(provideCoin);
                    memberRepository.save(member);
                },
                () -> {
                    throw new ItemNotFoundException("존재하지 않는 사원입니다. " + memberId);
                }
            );

        }

        // TODO 사원별 이력 로직 추가

    }
}
