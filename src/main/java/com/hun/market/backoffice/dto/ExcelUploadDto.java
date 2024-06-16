package com.hun.market.backoffice.dto;

import com.hun.market.backoffice.aop.ExcelColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExcelUploadDto {

    @ExcelColumn
    private String userName;
    @ExcelColumn
    private Long coin;

    @Override public String toString() {
        return "ExcelUploadDto{" +
            "userName='" + userName + '\'' +
            ", coin=" + coin +
            '}';
    }
}
