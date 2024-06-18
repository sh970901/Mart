package com.hun.market.backoffice.service;

import com.hun.market.backoffice.dto.EmployeeExcelUploadDto;
import com.hun.market.backoffice.enums.ExcelUploadType;
import com.hun.market.item.dto.ItemDto;
import com.hun.market.item.dto.ItemDto.ItemCreateRequestDto;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Override
    public void uploadExcel(MultipartFile file, ExcelUploadType uploadType) throws IOException {
        //Enum 타입에 따라 클래스 타입을 받아오게 할 수 있을까? ex) getCode

        if(ExcelUploadType.EMPLOYEE.equals(uploadType)){
            List<EmployeeExcelUploadDto> resultDtoList = new OneSheetExcelUploader<>(EmployeeExcelUploadDto.class, file).getResultDtoList();
            //Todo 엑셀업로드를 통해 신규 사원 생성 부분(비밀번호는 어떻게?)
            // 아니면 있는 사람에 한해서 코인을 정보를 수정하는 방향으로 갈지??
            // 부서까지 분리를 해놓으셨군요,, 상당히 힘든길이되겠습니다,,
            // resultDtoList -> Entity 변환 작업 필요
            // EmployeeExcelUploadDto는 임시
        } else if(ExcelUploadType.ITEM.equals(uploadType)) {
            //Todo ItemDto (그냥 dto 도 임시, 객체만 추가)
            // createDto 생성자 접근레벨 PRIVATE ㅜ
            List<ItemDto> resultDtoList = new OneSheetExcelUploader<>(ItemDto.class, file).getResultDtoList();
            for (ItemDto itemDto : resultDtoList) {
                System.out.println(resultDtoList);
            }
            // Dto to Entity 로직 필요
        }

    }
}
