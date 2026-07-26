package io.github.stock_control_api.dto.v1.user;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String firstName,
        String lastName,
        String email
) {}
