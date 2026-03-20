package br.com.stock_control_api.dto.user;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
