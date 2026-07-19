package io.github.stock_control_api.handler;

import io.github.stock_control_api.builder.ResponseBuilder;
import io.github.stock_control_api.dto.v1.ApiResponse;
import io.github.stock_control_api.exception.ResourceAlreadyExistsException;
import io.github.stock_control_api.exception.ResourceNotFoundException;
import io.github.stock_control_api.exception.product.ProductAlreadyExistsException;
import io.github.stock_control_api.exception.product.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseBuilder.<Void>builder()
                .message(ex.getMessage())
                .notFound();
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException ex
            ) {
        return ResponseBuilder.<Void>builder()
                .message(ex.getMessage())
                .conflict();
    }
}
