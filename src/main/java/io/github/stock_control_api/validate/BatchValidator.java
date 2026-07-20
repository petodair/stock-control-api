package io.github.stock_control_api.validate;

import io.github.stock_control_api.entity.Batch;
import io.github.stock_control_api.exception.InvalidDateException;
import io.github.stock_control_api.repository.BatchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BatchValidator {
    private final BatchRepository batchRepository;

    public void dateValidator(Batch batch) {
        LocalDate manufacturing = batch.getManufacturing();
        LocalDate validity = batch.getValidity();
        LocalDate receivedAt = batch.getReceivedAt();

        if (manufacturing == null || validity == null) {
            throw new InvalidDateException("As datas de fabricação e validade são obrigatórias.");
        }
        if(validity.isBefore(manufacturing)) {
            throw new InvalidDateException("A data de validade não pode ser antes" +
                    " da data de fabricação");
        }
        if( (receivedAt != null) && receivedAt.isBefore(manufacturing)) {
            throw new InvalidDateException("A data de recebimento não pode ser antes da " +
                    "data de fabricação");
        }
        if(manufacturing.isAfter(LocalDate.now())) {
            throw new InvalidDateException("A data de fabricação não pode ser depois " +
                    "da data de hoje");
        }
    }
}
