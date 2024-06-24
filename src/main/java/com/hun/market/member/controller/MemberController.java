package com.hun.market.member.controller;

import com.hun.market.member.domain.MemberContext;
import com.hun.market.member.dto.MemberDto;
import com.hun.market.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/m")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/main")
    public String myInfo(Model model,
                         @AuthenticationPrincipal MemberContext memberSession){
        MemberDto.MemberResponseDto memberDto = memberService.getMember(memberSession.getMemberId());
        model.addAttribute("memberDto", memberDto);
        return "member/my_info";
    }
}
