package com.mitchmele.grievbox.controller;

import com.mitchmele.grievbox.model.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage handleException(RuntimeException ex) {
        log.error("Api Exception: {}", ex.getLocalizedMessage());
        return ErrorMessage.builder()
                .message(ex.getLocalizedMessage())
                .status(500)
                .build();
    }
}
