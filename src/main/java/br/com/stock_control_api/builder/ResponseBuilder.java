package br.com.stock_control_api.builder;

import br.com.stock_control_api.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
    private ResponseBuilder() {}

    public static <T> ResponseEntity<ApiResponse<T>> success(HttpStatusCode code, String message, T body){
        return new ResponseEntity<>(
                new ApiResponse<>(code, message, body),
                code
        );
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T body){
        return success(HttpStatus.OK, "Operação realizada com sucesso!", body);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatusCode code, String message){
        return new ResponseEntity<>(
                new ApiResponse<>(code, message, null),
                code
        );
    }
}
