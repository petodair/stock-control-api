package br.com.stock_control_api.configuration;

import br.com.stock_control_api.builder.ResponseBuilder;
import br.com.stock_control_api.dto.ApiResponse;
import br.com.stock_control_api.dto.FieldErrorResponse;
import br.com.stock_control_api.exception.IllegalDateException;
import br.com.stock_control_api.exception.ResourceConflictException;
import br.com.stock_control_api.exception.product.ProductCodeAlreadyExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceConflictException(ResourceConflictException ex){
        return ResponseBuilder.error(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleEntityNotFoundException(EntityNotFoundException ex){
        return ResponseBuilder.error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex){
        return ResponseBuilder.error(HttpStatus.UNPROCESSABLE_CONTENT, "Erro ao tentar salvar, verifique se todos os " +
                "campos estão digitados corretamente.");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<List<FieldErrorResponse>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<FieldErrorResponse> errorList = ex.getFieldErrors()
                .stream()
                .map(fe -> new FieldErrorResponse(fe.getField(), fe.getDefaultMessage()))
                .toList();
        return ResponseBuilder.error(HttpStatus.UNPROCESSABLE_CONTENT, "Erro ao validar campos",
                errorList);
    }

    @ExceptionHandler(IllegalDateException.class)
    public ResponseEntity<ApiResponse<List<FieldErrorResponse>>> handleIllegalDateException(IllegalDateException ex){
        return ResponseBuilder.error(HttpStatus.UNPROCESSABLE_CONTENT, ex.getMessage());
    }
}
