package ru.bellintegrator.practice.controller.advice.exception;

/**
 * Создаем ResponseMsg model для @RestControllerAdvice
 * Нужен для отображения сообщения об ошибке
 */
public class ErrorView {
    private String error;

    public ErrorView(String msg) {
        this.error = msg;
    }

    public String getMessage() {
        return error;
    }

    public void setMessage(String message) {
        this.error = message;
    }
}
