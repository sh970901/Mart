package com.hun.market.member.controller;

import com.hun.market.member.dto.MemberDto;
import com.hun.market.member.dto.MemberDto.MemberRequestDto;
import com.hun.market.member.dto.MemberDto.MemberResponseDto;
import com.hun.market.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/m")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/modify/{memberId}")
    public void updateEmployee(@PathVariable Long memberId, @RequestBody MemberRequestDto memberModifyRequestDto) {
        memberService.updateMember(memberId, memberModifyRequestDto);
        // TODO 결과 반환은  api 재 호출로(화면단)
    }

    @GetMapping("/employee")
    public List<MemberResponseDto> getAllMembers() {
        return memberService.getAllMembers();
    }

//    @GetMapping("/member/{id}")
//    public String memberInfo(@PathVariable(value = "id") Long memberId){
//        System.out.println(memberId);
//        return "member/my_info";
//    }

}
