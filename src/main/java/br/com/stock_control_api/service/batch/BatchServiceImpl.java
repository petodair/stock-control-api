package br.com.stock_control_api.service.batch;

import br.com.stock_control_api.dto.batch.BatchRequestDTO;
import br.com.stock_control_api.dto.batch.BatchResponseDTO;
import br.com.stock_control_api.entity.Batch;
import br.com.stock_control_api.entity.Product;
import br.com.stock_control_api.enums.BatchLocal;
import br.com.stock_control_api.mapper.BatchMapper;
import br.com.stock_control_api.repository.BatchRepository;
import br.com.stock_control_api.repository.ProductRepository;
import br.com.stock_control_api.validator.batch.BatchValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
    public BatchResponseDTO save(BatchRequestDTO dto) {
        this.batchValidator.validate(dto);

        Batch batch = BatchMapper.toEntity(dto);

        Product product = this.productRepository.findById(dto.productId())
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + dto.productId()));

        batch.setProduct(product);
        return BatchMapper.toDTO(batchRepository.save(batch));
    }

    @Override
    public BatchResponseDTO update(Long id, BatchRequestDTO dto) {
        Batch batch = this.batchRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Lote com o ID " + id + " não " +
                        "encontrado para atualização"
        ));

        if (!batch.getBatchNumber().equals(dto.batchNumber())) {
            this.batchValidator.validate(dto);
        }

        if (!batch.getProduct().getId().equals(dto.productId())) {
            Product newProduct = this.productRepository.findById(dto.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado com ID: " + dto.productId()));
            batch.setProduct(newProduct);
        }

        batch.setBatchNumber(dto.batchNumber());
        batch.setManufacturingDate(dto.manufacturingDate());
        batch.setExpirationDate(dto.expirationDate());
        batch.setBatchLocal(dto.batchLocal());

        return BatchMapper.toDTO(batchRepository.save(batch));
    }

    @Override
    public List<BatchResponseDTO> findAll(String batchNumber, BatchLocal batchLocal) {
        return this.batchRepository.findWithFilters(batchNumber, batchLocal).stream().map(
                BatchMapper::toDTO
        ).toList();
    }

    @Override
     public List<BatchResponseDTO> findByExample(String batchNumber, BatchLocal batchLocal){
        Batch batch = new Batch();
        batch.setBatchNumber(batchNumber);
        batch.setBatchLocal(batchLocal);
         ExampleMatcher matcher = ExampleMatcher
                 .matching()
                 .withIgnoreNullValues()
                 .withIgnoreCase()
                 .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
         Example<Batch> example = Example.of(batch, matcher);
         return this.batchRepository.findAll(example)
                 .stream()
                 .map(BatchMapper::toDTO)
                 .toList();
     }

    @Override
    public BatchResponseDTO findById(Long id) {
        return this.batchRepository.findById(id).map(BatchMapper::toDTO).orElseThrow(
                () -> new EntityNotFoundException("Lote com o ID " + id + " não encontrado")
        );
    }

    @Override
    public void delete(Long id) {
        if (!this.batchRepository.existsById(id)) {
            throw new EntityNotFoundException("Lote com o ID " + id + " não encontrado" +
                    " para exclusão");
        }
        this.batchRepository.deleteById(id);
    }
}
