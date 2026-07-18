package io.github.stock_control_api.validate;

import io.github.stock_control_api.exception.producttype.ProductTypeAlreadyException;
import io.github.stock_control_api.repository.ProductTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductTypeValidate {

    private final ProductTypeRepository productTypeRepository;

    private void existsByName(String name) {
        if(this.productTypeRepository.existsByName(name)){
            throw new ProductTypeAlreadyException("Já existe um tipo de produto cadastrado " +
                    "com esse nome");
        }
    }
}
