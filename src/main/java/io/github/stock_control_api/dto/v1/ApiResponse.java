package io.github.stock_control_api.dto.v1;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatusCode;

import java.time.Instant;

@Schema(
        description = "DTO de resposta padronizada ao cliente"
)
public record ApiResponse<D>(
        @Schema(
                description = "Resposta geral da solicitação. " +
                        "Se falhou ou se foi um sucesso",
                example = "SUCCESS"
        )
        ResponseStatus responseStatus,
        int statusCode,
        String message,

        @Schema(
                description = "Dado a ser enviado. Serve como o corpo principal da resposta"
        )
        D data,

        @Schema(
                description = "Registra o momento exato em que a resposta foi criada"
        )
        Instant timestamp
) {
    public ApiResponse(ResponseStatus responseStatus, HttpStatusCode code, String message, D data) {
        this(responseStatus, code.value(), message, data, Instant.now());
    }

    public enum ResponseStatus{
            ERROR,
            SUCCESS
    }
}
