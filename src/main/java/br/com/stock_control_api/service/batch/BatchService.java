package br.com.stock_control_api.service.batch;

import br.com.stock_control_api.dto.batch.BatchRequestDTO;
import br.com.stock_control_api.entity.Batch;
import br.com.stock_control_api.enums.BatchLocal;

import java.util.List;

public interface BatchService {
    Batch save(BatchRequestDTO dto);
    Batch update(Long id, BatchRequestDTO dto);
    List<Batch> findAll(String batchNumber, BatchLocal batchLocal);
    Batch findById(Long id);
    void delete(Long id);
}
