package com.hun.market.backoffice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BackOfficeController {

    @GetMapping("/backoffice/home")
    public String backOfficeHomeView() {
        return "backoffice/backoffice";
    }
}
