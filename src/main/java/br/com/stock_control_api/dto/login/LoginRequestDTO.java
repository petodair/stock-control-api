package br.com.stock_control_api.dto.login;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(
        @NotBlank
        String email,

        @NotBlank
        String password
) {
}
