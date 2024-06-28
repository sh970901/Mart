package com.hun.market.member.service;

import com.hun.market.backoffice.dto.CoinProvideRequestDto;
import com.hun.market.core.util.RandomStringGenerator;
import com.hun.market.item.exception.ItemNotFoundException;
import com.hun.market.member.domain.CoinTransHistory;
import com.hun.market.member.domain.Member;
import com.hun.market.member.dto.MemberDto;
import com.hun.market.member.dto.MemberDto.MemberRequestDto;
import com.hun.market.member.dto.MemberDto.MemberResponseDto;
import com.hun.market.member.exception.MemberNotMatchException;
import com.hun.market.member.repository.MemberRepository;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
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

        coinProvideRequestDto.getEmployeeList().forEach(memberId ->
            memberRepository.findById(memberId)
                            .ifPresentOrElse(
                                member -> {
                                    /*실제 코인 지급 부분*/
                                    member.provideCoin(provideCoin);
                                    /*이력 추가 부분*/
                                    member.getCoinTransHistories().add(CoinTransHistory.createDepositTransaction(member, coinProvideRequestDto));
                                    memberRepository.save(member);
                                },
                                () -> {
                                    throw new ItemNotFoundException("존재하지 않는 사원입니다. " + memberId);
                                }
                            )
        );

    }

    @Override
    public List<MemberResponseDto> getAllMembers() {

        return memberRepository.findAllByOrderById().stream()
                               .map(MemberDto::from)
                               .collect(Collectors.toList());

    }

    @Override
    public MemberResponseDto getMember(Long memberId) {

        return memberRepository.findById(memberId)
                               .map(MemberDto::from)
                               .orElseThrow(() -> new MemberNotMatchException("존재하지 않는 사원입니다. " + memberId));
    }

    @Override
    public MemberResponseDto getMember(String email) {

        return memberRepository.findByMbEmail(email)
                .map(MemberDto::from)
                .orElseThrow(() -> new MemberNotMatchException("존재하지 않는 사원입니다. " + email));
    }

    @Override
    @Transactional
    public void updateMember(Long memberId, MemberRequestDto memberRequestDto) {
        memberRepository.findById(memberId)
                        .ifPresentOrElse(
                            member -> {
                                member.modify(memberRequestDto);
                                memberRepository.save(member);
                            },
                            () -> {
                                throw new MemberNotMatchException("존재하지 않는 사원입니다. " + memberId);
                            }
                        );

    }

    @Transactional
    @Override
    public void updatePassword(Long memberId, String encodingPwd) {
        Member member = memberRepository.findById(memberId).orElseThrow(()-> new MemberNotMatchException("존재하지 않는 사원입니다."));
        member.modifyPassword(encodingPwd);
        memberRepository.save(member);
    }

    @Transactional
    @Override
    public void resetPassword(String email) {
        Member member = memberRepository.findByMbEmail(email).orElseThrow(()-> new MemberNotMatchException("존재하지 않는 사원입니다."));
        String newPassword = RandomStringGenerator.generateRandomString(20);
        member.resetPassword(newPassword);
        memberRepository.save(member);
    }

}
