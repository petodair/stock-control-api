package io.github.stock_control_api.dto.v1.user;

import java.util.List;

public record UserUpdateRequestDTO(
        String userName,
        String lastName,
        List<String> authorities
) {
}
