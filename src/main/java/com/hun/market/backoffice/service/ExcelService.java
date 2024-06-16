package com.hun.market.backoffice.service;

import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {

    void uploadExcel(MultipartFile file) throws IOException;
}
