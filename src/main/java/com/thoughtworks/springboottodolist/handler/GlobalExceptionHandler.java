package com.thoughtworks.springboottodolist.handler;

import com.thoughtworks.springboottodolist.exception.BusinessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    public String handleBusinessException(Exception e){
        return e.getMessage();
    }
}
