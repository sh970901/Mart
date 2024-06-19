package com.hun.market.member.service;

import com.hun.market.backoffice.dto.CoinProvideRequestDto;
import com.hun.market.member.dto.MemberDto.MemberResponseDto;
import java.util.List;

public interface MemberService {

    void provideCoin(CoinProvideRequestDto coinProvideRequestDto);

    List<MemberResponseDto> getAllMembers();

    MemberResponseDto getMember(Long memberId);
}
