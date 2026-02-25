package br.com.stock_control_api.builder;

import br.com.stock_control_api.dto.ApiResponse;
import br.com.stock_control_api.dto.FieldErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

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

    public static <T> ResponseEntity<ApiResponse<T>> created(URI uri, String message, T body){
        return ResponseEntity
                .created(uri)
                .body(new ApiResponse<>(HttpStatus.CREATED,message,body));
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(HttpStatusCode code, String message){
        return new ResponseEntity<>(
                new ApiResponse<>(code, message, null),
                code
        );
    }

    public static <T> ResponseEntity<ApiResponse<List<FieldErrorResponse>>> error(
            HttpStatusCode code,
            String message,
            List<FieldErrorResponse> fieldErrors){
        return new ResponseEntity<>(
                new ApiResponse<>(code, message, fieldErrors),
                code
        );
    }
}
