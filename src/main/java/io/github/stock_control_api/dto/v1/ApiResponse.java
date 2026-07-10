package io.github.stock_control_api.dto.v1;

import org.springframework.http.HttpStatusCode;

import java.time.Instant;

public record ApiResponse<D>(
        ResponseStatus responseStatus,
        int statusCode,
        String message,
        D data,
        Instant timestamp
) {
    public ApiResponse(ResponseStatus responseStatus, HttpStatusCode code, String message, D data) {
        this(responseStatus, code.value(), message, data, Instant.now());
    }

    public enum ResponseStatus{
            ERROR,
            SUCCESS
    }
}
