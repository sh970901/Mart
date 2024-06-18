package com.hun.market.backoffice.service;

import com.hun.market.backoffice.enums.ExcelUploadType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ExcelService {

    void uploadExcel(MultipartFile file, ExcelUploadType uploadType) throws IOException;
}
