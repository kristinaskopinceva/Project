package ru.bellintegrator.practice.controller.advice.exception;

/**
 * Создаем ResponseMsg model для @RestControllerAdvice
 * Нужен для отображения сообщения об ошибке
 */
public class ErrorView {
    private String error;

    public ErrorView(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }


}
