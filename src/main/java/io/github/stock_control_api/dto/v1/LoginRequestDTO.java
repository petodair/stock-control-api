package io.github.stock_control_api.dto.v1;

public record LoginRequestDTO(
        String email,
        String password
) {
}
