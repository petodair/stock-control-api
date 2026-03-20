package br.com.stock_control_api.service.batch;

import br.com.stock_control_api.dto.batch.BatchResponseDTO;

import java.util.List;

public interface BatchPdfService {
    byte[] generatePdf(List<BatchResponseDTO> batches);
}
