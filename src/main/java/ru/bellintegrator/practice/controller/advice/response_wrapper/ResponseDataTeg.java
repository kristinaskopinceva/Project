package ru.bellintegrator.practice.controller.advice.response_wrapper;
/**
 * Доавляем data для body ответа
 *
 */
public class ResponseDataTeg <T> {

    private T data;

    public ResponseDataTeg(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
