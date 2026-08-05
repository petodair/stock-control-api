package io.github.stock_control_api.dto.v1.enterprise;

import io.github.stock_control_api.entity.User;

public record EnterpriseRequestDTO(
        String name,
        User admin
) {
}
