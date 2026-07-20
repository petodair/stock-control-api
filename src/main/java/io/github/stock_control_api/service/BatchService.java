package io.github.stock_control_api.service;

import io.github.stock_control_api.entity.Batch;
import io.github.stock_control_api.exception.batch.BatchNotFoundException;
import io.github.stock_control_api.repository.BatchRepository;
import io.github.stock_control_api.validate.BatchValidate;
import io.github.stock_control_api.validate.ProductValidate;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {
    private final BatchRepository batchRepository;
    private final BatchValidate batchValidate;
    private final ProductValidate productValidate;

    public Batch findById(long id){
        return batchRepository.findById(id).orElseThrow(() ->
            new BatchNotFoundException(
                    "lote com o id:" + id + " não encontrado"
                )
        );
    }

    public List<Batch> findAll(){
        return batchRepository.findAll();
    }

    @Transactional
    public Batch save(Batch batch) {
        this.batchValidate.dateValidator(batch);
        if(batch.getProduct() == null){
            throw new IllegalArgumentException("O lote precisa de um produto");
        }
        this.productValidate.existsById(batch.getProduct().getId());
        return batchRepository.save(batch);
    }

    @Transactional
    public Batch update(Batch batch, Long id) {
        Batch batchToUpdate = findById(id);
        this.productValidate.existsById(batchToUpdate.getProduct().getId());
        this.batchValidate.toUpdate(batch, batchToUpdate);
        return this.batchRepository.save(batchToUpdate);
    }

    @Transactional
    public void deleteById(Long id) {
        this.batchValidate.existsById(id);
        this.batchRepository.deleteById(id);
    }

}
