//package com.hun.market.core.exception;
//
//import com.hun.market.core.response.CommonResponse;
//import com.hun.market.core.response.DescriptionResponseDto;
//import com.hun.market.item.exception.ItemNotFoundException;
//import com.hun.market.order.cart.exception.CartNotFoundException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//
//import java.io.IOException;
//
//@Slf4j
//@ControllerAdvice
//public class ErrorControllerAdvice {
//    @ResponseStatus(HttpStatus.OK)
//    @ExceptionHandler(value = {
//            ItemNotFoundException.class,
//            UsernameNotFoundException.class,
//            CartNotFoundException.class
//    })
//    public void RuntimeException(HttpServletRequest request, HttpServletResponse response, Exception throwable) throws IOException {
//        log.info("{}", throwable.getMessage());
//        throwable.printStackTrace();
//        response.sendError(HttpServletResponse.SC_OK, throwable.getMessage());
//    }
//
//}
