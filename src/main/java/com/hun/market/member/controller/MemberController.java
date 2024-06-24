package com.hun.market.member.controller;

import com.hun.market.member.domain.MemberContext;
import com.hun.market.member.dto.MemberDto;
import com.hun.market.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/m")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;


    @GetMapping("/main")
    public String myInfo(Model model,
                         @AuthenticationPrincipal MemberContext memberSession){
        MemberDto.MemberResponseDto memberDto = memberService.getMember(memberSession.getMemberId());
        model.addAttribute("memberDto", memberDto);
        return "member/my_info";
    }

    @PostMapping("/change-password")
    public String changePassword(Model model,
        @AuthenticationPrincipal MemberContext memberSession, @ModelAttribute MemberDto.MemberChangePwdRequestDto mbrChangePwdDto, RedirectAttributes redirectAttributes){

        if (!passwordEncoder.matches(mbrChangePwdDto.getCurrentPassword(), memberSession.getMbPassword())) {

            redirectAttributes.addFlashAttribute("error", "현재 세션 값 불일치");
            System.out.println("1");
            return "redirect:/m/change-password";
        }

        if (!mbrChangePwdDto.getNewPassword().equals(mbrChangePwdDto.getConfirmPassword())) {
            redirectAttributes.addFlashAttribute("error", "새 비밀번호가 일치하지 않습니다.");
            System.out.println("2");
            return "redirect:/change-password";
        }

//        userService.updatePassword(userDetails.getUsername(), passwordEncoder.encode(newPassword));
//
//        // Logout the user after password change
//        new SecurityContextLogoutHandler().logout(request, response, auth);

        redirectAttributes.addFlashAttribute("message", "비밀번호가 변경되었습니다. 다시 로그인해주세요.");



        return "redirect:/login";
    }
}
