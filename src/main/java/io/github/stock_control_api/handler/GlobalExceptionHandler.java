package io.github.stock_control_api.handler;

import io.github.stock_control_api.builder.ResponseBuilder;
import io.github.stock_control_api.dto.v1.ApiResponse;
import io.github.stock_control_api.exception.product.ProductAlreadyExistsException;
import io.github.stock_control_api.exception.product.ProductNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductNotFoundException(ProductNotFoundException ex) {
        return ResponseBuilder.<Void>builder()
                .message(ex.getMessage())
                .notFound();
    }

    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<ApiResponse<Void>> handleProductAlreadyExistsException(
            ProductAlreadyExistsException ex
            ) {
        return ResponseBuilder.<Void>builder()
                .message(ex.getMessage())
                .conflict();
    }
}
