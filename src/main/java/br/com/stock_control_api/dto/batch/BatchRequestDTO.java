package br.com.stock_control_api.dto.batch;

import br.com.stock_control_api.enums.BatchLocal;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record BatchRequestDTO(

        String batchNumber,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate manufacturingDate,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        LocalDate expirationDate,

        Long productId,
        BatchLocal batchLocal
) {
}
