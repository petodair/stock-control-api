package io.github.stock_control_api.dto.v1.product;

import java.math.BigDecimal;

public record ProductFilter(
        String name,
        String code,
        String productTypeName,
        BigDecimal minPrice,
        BigDecimal maxPrice
) {
}
