package br.com.stock_control_api.validator.batch;

import br.com.stock_control_api.dto.batch.BatchRequestDTO;
import br.com.stock_control_api.entity.Batch;

public interface BatchValidator {
    void validate(BatchRequestDTO dto);
    boolean existsByBatchNumber(String batchNumber);
}
