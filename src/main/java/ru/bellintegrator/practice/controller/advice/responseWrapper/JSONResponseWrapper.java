package ru.bellintegrator.practice.controller.advice.responseWrapper;

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
public class JSONResponseWrapper implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof ErrorView) {
            return body;
        } else {
            return new WrapperObj<Object>(body);
        }
    }

    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    @JsonSerialize
    private class WrapperObj<T> {
        private final Object data;

        public WrapperObj(Object data) {
            this.data = data;
        }
    }

    enum ResponseMsg {
        Success,
        NotFound,
        Failure
    }
}
