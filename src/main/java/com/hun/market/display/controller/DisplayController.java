package com.hun.market.display.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DisplayController {

    @GetMapping("/")
    public String display(){
        return "display/home";
    }
}
