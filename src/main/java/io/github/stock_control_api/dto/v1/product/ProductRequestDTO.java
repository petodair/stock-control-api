package io.github.stock_control_api.dto.v1.product;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String code,
        BigDecimal price
) {
}
