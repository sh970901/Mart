package com.hun.market.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/m")
@RequiredArgsConstructor
public class MemberApiController {

//    @GetMapping("/member/{id}")
//    public String memberInfo(@PathVariable(value = "id") Long memberId){
//        System.out.println(memberId);
//        return "member/my_info";
//    }
}
