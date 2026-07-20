package io.github.stock_control_api.validate;

import io.github.stock_control_api.entity.Batch;
import io.github.stock_control_api.entity.Product;
import io.github.stock_control_api.exception.InvalidDateException;
import io.github.stock_control_api.exception.batch.BatchNotFoundException;
import io.github.stock_control_api.repository.BatchRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BatchValidate {

    private final BatchRepository batchRepository;

    public void dateValidator(Batch batch) {
        LocalDate manufacturing = batch.getManufacturing();
        LocalDate validity = batch.getValidity();
        LocalDate receivedAt = batch.getReceivedAt();

        if (manufacturing == null || validity == null) {
            throw new InvalidDateException("As datas de fabricação e validade são obrigatórias.");
        }
        if(validity.isBefore(manufacturing)) {
            throw new InvalidDateException("A data de validade não pode ser antes" +
                    " da data de fabricação");
        }
        if( (receivedAt != null) && receivedAt.isBefore(manufacturing)) {
            throw new InvalidDateException("A data de recebimento não pode ser antes da " +
                    "data de fabricação");
        }
        if(manufacturing.isAfter(LocalDate.now())) {
            throw new InvalidDateException("A data de fabricação não pode ser depois " +
                    "da data de hoje");
        }
    }

    public void existsById(Long id){
        if(!this.batchRepository.existsById(id)){
            throw new BatchNotFoundException(
                    "lote com o id:" + id + " não encontrado para a operação"
            );
        }
    }

    public void toUpdate(Batch newBatch, Batch toUpdate){
        if(ObjectUtils.isNotEmpty(newBatch.getManufacturing())){
            toUpdate.setManufacturing(newBatch.getManufacturing());
        }
        if(ObjectUtils.isNotEmpty(newBatch.getValidity())){
            toUpdate.setValidity(newBatch.getValidity());
        }
        if(ObjectUtils.isNotEmpty(newBatch.getQuantity())){
            toUpdate.setQuantity(newBatch.getQuantity());
        }
        if(StringUtils.isNotBlank(newBatch.getBatchNumber())){
            toUpdate.setBatchNumber(newBatch.getBatchNumber());
        }
        if(ObjectUtils.isNotEmpty(newBatch.getLocation())){
            toUpdate.setLocation(newBatch.getLocation());
        }
        if(ObjectUtils.isNotEmpty(newBatch.getReceivedAt())){
            toUpdate.setReceivedAt(newBatch.getReceivedAt());
        }
        if(ObjectUtils.isNotEmpty(newBatch.getProduct())){
            toUpdate.setProduct(newBatch.getProduct());
        }
        dateValidator(toUpdate);
    }
}
