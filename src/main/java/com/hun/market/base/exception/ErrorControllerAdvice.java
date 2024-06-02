package com.hun.market.base.exception;

import com.hun.market.item.exception.ItemNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;

@Slf4j
@ControllerAdvice
public class ErrorControllerAdvice {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = {
            ItemNotFoundException.class
    })
    public void RuntimeException(HttpServletRequest request, HttpServletResponse response, Exception throwable) throws IOException {
        log.info("{}", throwable.getMessage());
        throwable.printStackTrace();
        response.sendError(HttpServletResponse.SC_OK, throwable.getMessage());
    }
}
