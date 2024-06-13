package com.hun.market.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/myinfo")
    public String myInf(){
        return "my/my_info";
    }
}
