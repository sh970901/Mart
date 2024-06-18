package com.hun.market.backoffice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeExcelUploadDto {

    private String employeeName;

    private String teamName;

    private Long coin;

    @Override public String toString() {
        return "EmployeeExcelUploadDto{" +
            "employeeName='" + employeeName + '\'' +
            ", teamName='" + teamName + '\'' +
            ", coin=" + coin +
            '}';
    }
}
