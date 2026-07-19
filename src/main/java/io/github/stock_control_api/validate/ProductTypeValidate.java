package io.github.stock_control_api.validate;

import io.github.stock_control_api.exception.producttype.ProductTypeAlreadyExistsException;
import io.github.stock_control_api.exception.producttype.ProductTypeNotFoundException;
import io.github.stock_control_api.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductTypeValidate {

    private final ProductTypeRepository productTypeRepository;

    public void existsById(Long id){
        if(!this.productTypeRepository.existsById(id)){
            throw new ProductTypeNotFoundException("Produto com id:" + id + " não encontrado.");
        }
    }

    public void existsByName(String name) {
        if(this.productTypeRepository.existsByName(name)){
            throw new ProductTypeAlreadyExistsException("Já existe um tipo de produto cadastrado " +
                    "com esse nome");
        }
    }
}
