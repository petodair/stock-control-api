package br.com.stock_control_api.dto.product;

import br.com.stock_control_api.enums.MeatType;
import br.com.stock_control_api.enums.StorageType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequestDTO(

        @NotBlank(message = "Campo obrigatório")
        String name,

        @NotBlank(message = "Campo obrigatório")
        String code,

        BigDecimal price,

        @NotNull(message = "Campo obrigatório")
        MeatType meatType,

        @NotNull(message = "Campo obrigatório")
        StorageType storageType
) {
}
