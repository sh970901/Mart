package com.hun.market.backoffice.dto;

import com.hun.market.backoffice.enums.EventType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

@Getter
public class CoinProvideRequestDto {

    @NotNull(message = "상품 재고는 필수값 입니다.(0개 이상)")
    @PositiveOrZero

    @NotNull(message = "이벤트 타입은 필수값 입니다.")
    private EventType eventType;

    private int coin;

    @NotNull(message = "이벤트 날짜는 필수값 입니다.")
    private LocalDate paymentDate;

    private List<Long> employeeList;

}
