package io.github.stock_control_api.dto.v1.enterprise;

import java.util.UUID;

public record EnterpriseResponseDTO(
        Long id,
        String name,
        UUID adminId,
        String adminName
) {
}
