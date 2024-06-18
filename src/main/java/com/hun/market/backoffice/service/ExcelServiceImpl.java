package com.hun.market.backoffice.service;

import com.hun.market.backoffice.dto.ExcelUploadDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void uploadExcel(MultipartFile file) throws IOException {
        //이 지점 또한 고민 // 반환값을 실제 데이터가 저장된 Dto로??
        //OneSheetExcelUploader 안에 필드로 dto 리스트를 만든다음 해당 리스트를 반환한다.
        OneSheetExcelUploader<ExcelUploadDto> oneSheetExcelUpload = new OneSheetExcelUploader<>(ExcelUploadDto.class, file);

    }
}
