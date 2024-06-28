package com.hun.market.member.controller;

import com.hun.market.core.exception.ResponseServiceException;
import com.hun.market.core.response.CommonResponse;
import com.hun.market.member.domain.Member;
import com.hun.market.member.dto.MemberDto;
import com.hun.market.member.dto.MemberDto.MemberRequestDto;
import com.hun.market.member.dto.MemberDto.MemberResponseDto;
import com.hun.market.member.exception.MemberNotMatchException;
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
    public MemberDto.MemberForgotPwdResponseDto sendPasswordEmail(@RequestBody MemberDto.MemberForgotPwdRequestDto forgotPwdRequestDto) {
        try{
            memberService.resetPassword(forgotPwdRequestDto.getEmail());
        }
        catch (MemberNotMatchException e){
            throw new ResponseServiceException(e.getMessage());
        }

        return MemberDto.MemberForgotPwdResponseDto.builder().description("등록된 메일 주소로 임시 비밀번호가 발송되었습니다.").build();
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
