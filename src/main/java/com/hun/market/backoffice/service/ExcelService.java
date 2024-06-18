package com.hun.market.backoffice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {

    void uploadExcel(MultipartFile file) throws IOException;
}
