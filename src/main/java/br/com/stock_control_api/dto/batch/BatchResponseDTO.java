package br.com.stock_control_api.dto.batch;

import br.com.stock_control_api.enums.BatchLocal;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BatchResponseDTO(
        Long id,
        String batchNumber,
        LocalDate manufacturingDate,
        LocalDate expirationDate,
        BatchLocal batchLocal,
        boolean isExpired,
        Long productId,
        String productName,
        String productCode,
        BigDecimal productPrice
) {
}
