package com.hun.market.backoffice.service;

import com.hun.market.backoffice.dto.ExcelUploadDto;
import java.io.IOException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void uploadExcel(MultipartFile file) throws IOException {
        //이 지점 또한 고민 // 반환값을 실제 데이터가 저장된 Dto로??
        OneSheetExcelUpload oneSheetExcelUpload = new OneSheetExcelUpload<>(ExcelUploadDto.class, file);
    }
}
