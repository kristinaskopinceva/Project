package ru.bellintegrator.practice.controller.advice.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Обрабатываем исключения Controller, создаем точку exception
 */
@RestControllerAdvice(basePackages = "ru.bellintegrator.practice.controller")
public class ExceptionHandlerController {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorView handleNotFoundException(CustomNotFoundException e) {
        ErrorView responseMsg = new ErrorView(e.getMessage());
        logger.error(e.getMessage());
        return responseMsg;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorView handleException(Exception e) {
        logger.error(e.getMessage());
        return new ErrorView("Внутреняя ошибка сервера!");
    }

}



