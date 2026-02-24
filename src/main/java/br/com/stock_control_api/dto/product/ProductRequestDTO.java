package br.com.stock_control_api.dto.product;

import br.com.stock_control_api.enums.MeatType;
import br.com.stock_control_api.enums.StorageType;

import java.math.BigDecimal;

public record ProductRequestDTO(
        String name,
        String code,
        BigDecimal price,
        MeatType meatType,
        StorageType storageType
) {
}
