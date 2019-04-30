package ru.bellintegrator.practice.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages ="ru.bellintegrator.practice.controller")
public class ExceptionHandlerController {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
     public String defaultErrorHandler(Exception e)  {
        return e.getMessage();}}