package com.hun.market.member.controller;

import com.hun.market.core.response.CommonResponse;
import com.hun.market.member.dto.MemberDto;
import com.hun.market.member.dto.MemberDto.MemberRequestDto;
import com.hun.market.member.dto.MemberDto.MemberResponseDto;
import com.hun.market.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/send-password-email")
    public CommonResponse<Object> sendPasswordEmail(@RequestBody String email) {
        System.out.println(email);
//        return CommonResponse.<String>from().data("ok").build();
        return CommonResponse.fail("등록되지 않은 계정입니다. \n관리자에게 문의 부탁드립니다.");
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
