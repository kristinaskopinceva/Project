package ru.bellintegrator.practice.exception;

public class MyException extends RuntimeException {

    public MyException(String message) {
        super(message);
    }

    public MyException(String message, Throwable throwable) {
        super(message, throwable);
    }

}