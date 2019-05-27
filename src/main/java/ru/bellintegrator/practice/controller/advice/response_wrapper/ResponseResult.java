package ru.bellintegrator.practice.controller.advice.response_wrapper;

public class ResponseResult {
    public Data data;

    public ResponseResult(String result) {
        this.data = new Data();
        this.data.result = result;
    }
    static class Data {
        public String result;
    }
}
