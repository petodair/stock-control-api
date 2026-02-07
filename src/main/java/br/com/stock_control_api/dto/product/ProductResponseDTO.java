package br.com.stock_control_api.dto.product;

import br.com.stock_control_api.enums.ProductType;

import java.math.BigDecimal;

public record ProductResponseDTO(
    Long id,
    String name,
    String code,
    BigDecimal price,
    ProductType type
)
{}
