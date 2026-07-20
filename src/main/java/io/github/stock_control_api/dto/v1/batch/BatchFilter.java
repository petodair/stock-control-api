package io.github.stock_control_api.dto.v1.batch;

import io.github.stock_control_api.entity.enums.Location;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BatchFilter(
        LocalDate validity,
        BigDecimal minQuantity,
        String batchNumber,
        Location location,
        LocalDate receivedAt,
        String productName,
        String productCode,
        String productType
) {
}
