package io.github.stock_control_api.dto.v1.product;

import java.math.BigDecimal;

public record ProductResponseDTO(
        Long id,
        String name,
        String code,
        BigDecimal price
) {
}
