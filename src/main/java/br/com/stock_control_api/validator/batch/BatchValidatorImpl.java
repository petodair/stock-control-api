package br.com.stock_control_api.validator.batch;

import br.com.stock_control_api.dto.batch.BatchRequestDTO;
import br.com.stock_control_api.entity.Batch;
import br.com.stock_control_api.exception.IllegalDateException;
import br.com.stock_control_api.exception.batch.BatchNumberAlreadyExists;
import br.com.stock_control_api.repository.BatchRepository;
import org.springframework.stereotype.Component;

@Component
public class BatchValidatorImpl implements BatchValidator {

    private final BatchRepository batchRepository;

    public BatchValidatorImpl(BatchRepository batchRepository) {
        this.batchRepository = batchRepository;
    }

    @Override
    public void validate(BatchRequestDTO dto) {
        if (batchRepository.existsByBatchNumber(dto.batchNumber())) {
            throw new BatchNumberAlreadyExists(dto.batchNumber());
        }
        if (dto.manufacturingDate().isAfter(dto.expirationDate())){
            throw new IllegalDateException("A data de fabricação não pode ser depois da data de validade");
        }
    }

    @Override
    public boolean existsByBatchNumber(String batchNumber) {
        return this.batchRepository.existsByBatchNumber(batchNumber);
    }
}
