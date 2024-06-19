package com.hun.market.backoffice.controller;

import com.hun.market.backoffice.dto.ItemModifyDto;
import com.hun.market.backoffice.enums.ExcelUploadType;
import com.hun.market.backoffice.service.ExcelService;
import com.hun.market.backoffice.service.ImageService;
import com.hun.market.item.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final ItemService itemService;

    // TODO 폼 제출할떄 요청URL 바꾸기 ㅜ
    @PostMapping("/upload/employees")
    public void uploadExcel(@RequestParam("employees") MultipartFile file) throws IOException {
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

    @PostMapping("/modify/item")
    public void updateItem(@Valid @RequestBody ItemModifyDto itemModifyDto) {
        itemService.updateItem(itemModifyDto);
        // TODO 결과 반환은  api 재 호출로(화면단)
    }




}
