package br.com.stock_control_api.mapper;

import br.com.stock_control_api.dto.batch.BatchRequestDTO;
import br.com.stock_control_api.dto.batch.BatchResponseDTO;
import br.com.stock_control_api.entity.Batch;

public abstract class BatchMapper {
    public static Batch toEntity(BatchRequestDTO dto) {
        Batch batch = new Batch();
        batch.setBatchNumber(dto.batchNumber());
        batch.setManufacturingDate(dto.manufacturingDate());
        batch.setExpirationDate(dto.expirationDate());
        batch.setBatchLocal(dto.batchLocal());
        batch.setQuantity(dto.quantity());
        return batch;
    }

    public static BatchResponseDTO toDTO(Batch batch) {
        return new BatchResponseDTO(
                batch.getId(),
                batch.getBatchNumber(),
                batch.getManufacturingDate(),
                batch.getExpirationDate(),
                batch.getBatchLocal(),
                batch.getQuantity(),
                batch.isExpired(),
                batch.getProduct().getId(),
                batch.getProduct().getName(),
                batch.getProduct().getCode(),
                batch.getProduct().getPrice()
        );
    }
}
