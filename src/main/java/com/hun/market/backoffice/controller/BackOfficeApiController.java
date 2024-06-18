package com.hun.market.backoffice.controller;

import com.hun.market.backoffice.enums.ExcelUploadType;
import com.hun.market.backoffice.service.ExcelService;
import com.hun.market.backoffice.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
@RequestMapping("/backoffice")
public class BackOfficeApiController {

    private final ExcelService excelService;
    private final ImageService imageService;

    @PostMapping("/upload/excel")
    public void uploadExcel(@RequestParam("excel") MultipartFile file) throws IOException {
        excelService.uploadExcel(file, ExcelUploadType.EMPLOYEE);
    }

    @PostMapping("/upload/image")
    public void uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        imageService.uploadImage(file);
    }

    @PostMapping("/upload/images")
    public void uploadMultiImage(@RequestParam("images") ArrayList<MultipartFile> file) throws IOException {
        imageService.uploadImages(file);
    }

    @PostMapping("/upload/items")
    public void uploadMultiItems(@RequestParam("items") MultipartFile file) throws IOException {
        excelService.uploadExcel(file, ExcelUploadType.ITEM);
    }


}
