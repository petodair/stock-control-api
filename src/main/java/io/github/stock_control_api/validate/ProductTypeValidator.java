package io.github.stock_control_api.validate;

import io.github.stock_control_api.entity.ProductType;
import io.github.stock_control_api.exception.producttype.ProductTypeAlreadyExistsException;
import io.github.stock_control_api.exception.producttype.ProductTypeNotFoundException;
import io.github.stock_control_api.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductTypeValidator implements Validator<ProductType>{

    private final ProductTypeRepository productTypeRepository;

    @Override
    public void shouldExists(ProductType entity){
        if(!this.productTypeRepository.existsById(entity.getId())){
            throw new ProductTypeNotFoundException("Produto com id:" + entity.getId() + " não encontrado.");
        }
    }

    @Override
    public void shouldNotExists(ProductType entity) {
        if(this.productTypeRepository.existsByName(entity.getName())){
            throw new ProductTypeAlreadyExistsException("Já existe um tipo de produto cadastrado " +
                    "com esse nome");
        }
    }

    @Override
    public void checkUpdate(ProductType newEntity, ProductType oldEntity) {

    }
}
