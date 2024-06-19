package com.hun.market.member.dto;

import com.hun.market.backoffice.dto.EmployeeExcelUploadDto;
import com.hun.market.member.domain.Department;
import com.hun.market.member.domain.Member;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

public class MemberDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class MemberCreateRequestDto {

        @NotNull(message = "member name is required")
        @Size(max = 100, message = "member name must be less than 100 characters")
        private String mbName;

        @NotNull(message = "password is required")
        private String mbPassword;

        @NotNull
        @PositiveOrZero
        private int mbCoin;

        private Department department;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class MemberResponseDto {

        private Long memberNo;

        private String memberName;

        private String memberPassword;

        private int memberCoin;

        private Department department;

    }

    public static MemberCreateRequestDto from(EmployeeExcelUploadDto excelUploadDto) {

        return MemberCreateRequestDto.builder()
                                     .mbName(excelUploadDto.getEmployeeName())
                                     .mbPassword(excelUploadDto.getEmployeeName())
                                     .mbCoin(excelUploadDto.getCoin())
                                     .department(Department.builder().departmentName(excelUploadDto.getDepartmentName()).teamName(excelUploadDto.getTeamName()).build())
                                     .build();
    }

    public static MemberResponseDto from(Member member) {
        return MemberResponseDto.builder()
                                .memberNo(member.getId())
                                .memberName(member.getMbName())
                                .memberPassword(member.getMbPassword())
                                .memberCoin(member.getMbCoin())
                                .department(member.getDepartment())
                                .build();
    }

}
