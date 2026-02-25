package br.com.stock_control_api.dto;

public record FieldErrorResponse(
        String fieldName,
        String errorMessage
) {
}
