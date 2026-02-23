package br.com.stock_control_api.configuration;

import br.com.stock_control_api.builder.ResponseBuilder;
import br.com.stock_control_api.dto.ApiResponse;
import br.com.stock_control_api.exception.ResourceConflictException;
import br.com.stock_control_api.exception.product.ProductCodeAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceConflictException(ResourceConflictException ex){
        return ResponseBuilder.error(HttpStatus.CONFLICT, ex.getMessage());
    }
}
