package io.github.stock_control_api.dto.v1.product;

import io.github.stock_control_api.entity.ProductType;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String code,
        BigDecimal price,
        ProductType productType
) {
}
