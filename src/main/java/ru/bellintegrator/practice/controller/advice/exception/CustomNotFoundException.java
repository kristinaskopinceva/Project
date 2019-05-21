package ru.bellintegrator.practice.controller.advice.exception;

/**
 * Кастомное исключение для RestController
 */
public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException(String msg) {
        super(msg);
    }
}