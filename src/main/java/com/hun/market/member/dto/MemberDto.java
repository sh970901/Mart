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
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @Builder
    public static class MemberRequestDto {

        @NotNull(message = "member name is required")
        @Size(max = 100, message = "member name must be less than 100 characters")
        private String mbName;

        @NotNull(message = "password is required")
        private String mbPassword;

        @NotNull(message = "email is required")
        private String mbEmail;

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

        private String memberEmail;

        private Department department;

    }

    @Getter
    @Setter
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    @AllArgsConstructor(access = AccessLevel.PUBLIC)
    @Builder
    public static class MemberChangePwdRequestDto {

//        @NotNull(message = "member name is required")
//        @Size(max = 100, message = "member name must be less than 100 characters")
//        private String mbName;

        @NotNull(message = "password is required")
        private String currentPassword;

        @NotNull(message = "password is required")
        private String newPassword;

        @NotNull(message = "password is required")
        private String confirmPassword;
    }


    public static MemberRequestDto from(EmployeeExcelUploadDto excelUploadDto) {

        return MemberRequestDto.builder()
                               .mbName(excelUploadDto.getEmployeeName())
                               .mbEmail(excelUploadDto.getEmail())
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
                                .memberEmail(member.getMbEmail())
                                .department(member.getDepartment())
                                .build();
    }



}
