package br.com.stock_control_api.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UserRequestDTO(
        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotNull
        List<String> roles
) {
}
