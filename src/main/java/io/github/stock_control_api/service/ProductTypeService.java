package io.github.stock_control_api.service;

import io.github.stock_control_api.entity.ProductType;
import io.github.stock_control_api.exception.producttype.ProductTypeNotFoundException;
import io.github.stock_control_api.repository.ProductTypeRepository;
import io.github.stock_control_api.validate.ProductTypeValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeValidate productTypeValidate;

    public ProductType findById(Long id){
        return this.productTypeRepository.findById(id).orElseThrow(() ->
                new ProductTypeNotFoundException("Produto com id:" + id + " não encontrado.")
        );
    }

    public List<ProductType> findAll(){
        return this.productTypeRepository.findAll();
    }

    public ProductType save(ProductType productType){
        this.productTypeValidate.existsByName(productType.getName());
        return this.productTypeRepository.save(productType);
    }

    public ProductType update(ProductType productType, Long id){
        ProductType toUpdate = this.findById(id);
        toUpdate.setName(productType.getName());
        return this.productTypeRepository.save(toUpdate);
    }

    public void deleteById(Long id){
        this.productTypeValidate.existsById(id);
        this.productTypeRepository.deleteById(id);
    }


}
