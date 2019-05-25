package ru.bellintegrator.practice.controller.advice.response_wrapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import ru.bellintegrator.practice.controller.advice.exception.ErrorView;

/**
 * Обертка для ответа от сервера
 */
@RestControllerAdvice(basePackages = "ru.bellintegrator.practice.controller")
public class JsonResponseWrapper implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ErrorView) {
            return body;
        } else if (body == null) {
            return new ResponseResult().data;
        } else {
            return new WrapperObject<Object>(body);
        }
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonSerialize
    private static class WrapperObject<T> {
        private final Object data;

        public WrapperObject(Object data) {
            this.data = data;
        }
    }
}
