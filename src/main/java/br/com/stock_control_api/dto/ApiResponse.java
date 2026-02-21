package br.com.stock_control_api.dto;

import org.springframework.http.HttpStatusCode;

import java.time.Instant;

public record ApiResponse<T>(
        Instant timeStamp,
        int code,
        String message,
        T data
) {
    public ApiResponse(HttpStatusCode code, String message, T data) {
        this(Instant.now(), code.value(), message, data);
    }
}
