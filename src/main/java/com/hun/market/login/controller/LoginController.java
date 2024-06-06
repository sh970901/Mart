package com.hun.market.login.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String login(){
        return "login/login_form";
    }
}
