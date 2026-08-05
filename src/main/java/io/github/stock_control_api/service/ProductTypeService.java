package io.github.stock_control_api.service;

import io.github.stock_control_api.entity.ProductType;
import io.github.stock_control_api.exception.producttype.ProductTypeNotFoundException;
import io.github.stock_control_api.repository.ProductTypeRepository;
import io.github.stock_control_api.validate.ProductTypeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository repository;
    private final ProductTypeValidator validator;

    public ProductType findById(Long id){
        return this.repository.findById(id).orElseThrow(() ->
                new ProductTypeNotFoundException("Produto com id:" + id + " não encontrado.")
        );
    }

    public List<ProductType> findAll(){
        return this.repository.findAll();
    }

    public ProductType save(ProductType productType){
        this.validator.shouldNotExists(productType);
        return this.repository.save(productType);
    }

    public ProductType update(ProductType productType, Long id){
        ProductType toUpdate = this.findById(id);
        toUpdate.setName(productType.getName());
        return this.repository.save(toUpdate);
    }

    public void deleteById(Long id){
        findById(id);
        this.repository.deleteById(id);
    }


}
