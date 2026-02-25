package br.com.stock_control_api.service.batch;

import br.com.stock_control_api.dto.batch.BatchRequestDTO;
import br.com.stock_control_api.entity.Batch;
import br.com.stock_control_api.entity.Product;
import br.com.stock_control_api.enums.BatchLocal;
import br.com.stock_control_api.mapper.BatchMapper;
import br.com.stock_control_api.repository.BatchRepository;
import br.com.stock_control_api.repository.ProductRepository;
import br.com.stock_control_api.validator.batch.BatchValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatchServiceImpl implements BatchService {

    private final BatchRepository batchRepository;
    private final ProductRepository productRepository;
    private final BatchValidator batchValidator;

    public BatchServiceImpl(BatchRepository batchRepository, ProductRepository productRepository,
                            BatchValidator batchValidator) {
        this.batchRepository = batchRepository;
        this.productRepository = productRepository;
        this.batchValidator = batchValidator;
    }

    @Override
    public Batch save(BatchRequestDTO dto) {
        this.batchValidator.validate(dto);

        Batch batch = BatchMapper.toEntity(dto);


        Product product = this.productRepository.findById(dto.productId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + dto.productId()));

        batch.setProduct(product);
        return this.batchRepository.save(batch);
    }

    @Override
    public Batch update(Long id, BatchRequestDTO dto) {
        Batch batch = this.batchRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Lote com o id " + id + " não " +
                        "encontrado para atualização"
        ));

        if (!batch.getBatchNumber().equals(dto.batchNumber())) {
            this.batchValidator.validate(dto);
        }

        batch.setBatchNumber(dto.batchNumber());
        batch.setManufacturingDate(dto.manufacturingDate());
        batch.setExpirationDate(dto.expirationDate());
        batch.setBatchLocal(dto.batchLocal());

        return this.batchRepository.save(batch);
    }

    @Override
    public List<Batch> findAll(String batchNumber, BatchLocal batchLocal) {
        return this.batchRepository.findWithFilters(batchNumber, batchLocal);
    }

    @Override
    public Batch findById(Long id) {
        return this.batchRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Lote com o id " + id + " não encontrado"));
    }

    @Override
    public void delete(Long id) {
        if (!this.batchRepository.existsById(id)) {
            throw new EntityNotFoundException("Lote com o id " + id + " não encontrado" +
                    " para exclusão");
        }
        this.batchRepository.deleteById(id);
    }
}
