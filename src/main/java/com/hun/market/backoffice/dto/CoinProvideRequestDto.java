package com.hun.market.backoffice.dto;

import com.hun.market.member.domain.CoinTransType;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

@Getter
public class CoinProvideRequestDto {


    @NotNull(message = "이벤트 타입은 필수값 입니다.")
    private CoinTransType coinTransType;

    private int coin;

    @NotNull(message = "이벤트 날짜는 필수값 입니다.")
    private LocalDate paymentDate;

    private String description;

    private List<Long> employeeList;

}
