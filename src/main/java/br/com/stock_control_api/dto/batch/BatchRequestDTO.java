package br.com.stock_control_api.dto.batch;

import br.com.stock_control_api.enums.BatchLocal;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BatchRequestDTO(

        @NotBlank
        String batchNumber,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate manufacturingDate,

        @NotNull
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate expirationDate,

        @NotNull
        Long productId,

        BatchLocal batchLocal,

        BigDecimal quantity
) {
}
