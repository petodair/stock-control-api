package br.com.stock_control_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;

//Essa anotação serve para ignorar campos nulos no JSON, por exemplo o campo data
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ApiResponse<T>(

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        Instant timeStamp,

        int code,

        String message,

        T data
) {
    public ApiResponse(HttpStatusCode code, String message, T data) {
        this(Instant.now(), code.value(), message, data);
    }
}
