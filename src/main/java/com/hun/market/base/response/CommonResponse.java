package com.hun.market.base.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "from")
public class CommonResponse<T> {

    @JsonIgnore
    private static final String MESSAGE_FAIL_RESULT = "fail";
    @JsonIgnore
    private static final String MESSAGE_FAIL_RESULT_CODE = "500";
    @JsonIgnore
    private static final String MESSAGE_SUCCESS_RESULT = "success";
    @JsonIgnore
    private static final String MESSAGE_SUCCESS_RESULT_CODE = "200";

    @Builder.Default
    private String resultCode = MESSAGE_SUCCESS_RESULT_CODE;

    @Builder.Default
    private String resultMessage = MESSAGE_SUCCESS_RESULT;

    private T data;

    public static CommonResponse<Object> fail(String message) {
        return CommonResponse.from()
                .resultCode(MESSAGE_FAIL_RESULT_CODE)
                .resultMessage(message)
                .build();
    }
}
