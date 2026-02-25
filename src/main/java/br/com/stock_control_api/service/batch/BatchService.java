package br.com.stock_control_api.service.batch;

import br.com.stock_control_api.dto.batch.BatchRequestDTO;
import br.com.stock_control_api.dto.batch.BatchResponseDTO;
import br.com.stock_control_api.entity.Batch;
import br.com.stock_control_api.enums.BatchLocal;

import java.util.List;

public interface BatchService {
    BatchResponseDTO save(BatchRequestDTO dto);
    BatchResponseDTO update(Long id, BatchRequestDTO dto);
    List<BatchResponseDTO> findAll(String batchNumber, BatchLocal batchLocal);
    BatchResponseDTO findById(Long id);
    void delete(Long id);
}
