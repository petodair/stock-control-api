package io.github.stock_control_api.dto.v1.batch;

import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.entity.enums.Location;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BatchRequestDTO(
        LocalDate manufacturing,
        LocalDate validity,
        BigDecimal quantity,
        String batchNumber,
        Location location,
        LocalDate receivedAt,
        Product product
) {
}
