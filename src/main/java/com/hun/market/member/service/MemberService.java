package com.hun.market.member.service;

import com.hun.market.backoffice.dto.CoinProvideRequestDto;
import com.hun.market.member.domain.CoinTransHistory;
import com.hun.market.member.dto.MemberDto.MemberCoinHistoryResponseDto;
import com.hun.market.member.dto.MemberDto.MemberRequestDto;
import com.hun.market.member.dto.MemberDto.MemberResponseDto;
import java.util.List;

public interface MemberService {

    void provideCoin(CoinProvideRequestDto coinProvideRequestDto);

    List<MemberResponseDto> getAllMembers();

    MemberResponseDto getMember(Long memberId);

    MemberResponseDto getMember(String email);

    void updateMember(Long memberId, MemberRequestDto memberRequestDto);

    void updatePassword(Long memberId, String encode);

    void resetPassword(String email);

    List<MemberCoinHistoryResponseDto> getMemberHistory(Long memberId);
}
