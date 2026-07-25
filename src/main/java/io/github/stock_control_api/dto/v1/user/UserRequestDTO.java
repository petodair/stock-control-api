package io.github.stock_control_api.dto.v1.user;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
