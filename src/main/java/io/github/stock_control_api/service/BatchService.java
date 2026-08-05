package io.github.stock_control_api.service;

import io.github.stock_control_api.dto.v1.batch.BatchFilter;
import io.github.stock_control_api.entity.Batch;
import io.github.stock_control_api.exception.batch.BatchNotFoundException;
import io.github.stock_control_api.repository.BatchRepository;
import io.github.stock_control_api.specification.BatchSpecification;
import io.github.stock_control_api.validate.BatchValidator;
import io.github.stock_control_api.validate.ProductValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {
    private final BatchRepository repository;
    private final BatchValidator validator;
    private final ProductValidator productValidator;

    public Batch findById(long id){
        return repository.findById(id).orElseThrow(() ->
            new BatchNotFoundException(
                    "lote com o id:" + id + " não encontrado"
                )
        );
    }

    public List<Batch> findAll(BatchFilter filter){
        return repository.findAll(BatchSpecification.withFilter(filter));
    }

    @Transactional
    public Batch save(Batch batch) {
        this.validator.dateValidator(batch);
        if(batch.getProduct() == null){
            throw new IllegalArgumentException("O lote precisa de um produto");
        }
        this.productValidator.shouldExists(batch.getProduct());
        return repository.save(batch);
    }

    @Transactional
    public Batch update(Batch batch, Long id) {
        Batch batchToUpdate = findById(id);
        this.productValidator.shouldExists(batchToUpdate.getProduct());
        this.validator.checkUpdate(batch, batchToUpdate);
        return this.repository.save(batchToUpdate);
    }

    @Transactional
    public void deleteById(Long id) {
        findById(id);
        this.repository.deleteById(id);
    }

}
