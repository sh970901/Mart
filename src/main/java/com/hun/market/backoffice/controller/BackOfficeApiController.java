package com.hun.market.backoffice.controller;

import com.hun.market.backoffice.service.ExcelService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/backoffice")
public class BackOfficeApiController {

    private final ExcelService excelService;

    @PostMapping("/uploadExcel")
    public void uploadExcel(@RequestParam("excel") MultipartFile file) throws IOException {
        excelService.uploadExcel(file);
    }
}