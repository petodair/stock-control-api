package io.github.stock_control_api.validate;

import io.github.stock_control_api.exception.product.ProductAlreadyExistsException;
import io.github.stock_control_api.exception.product.ProductNotFoundException;
import io.github.stock_control_api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProductValidate {

    private final ProductRepository productRepository;

    public void existsById(Long id){
        if(!productRepository.existsById(id)){
            throw new ProductNotFoundException("Produto não encontrado para a operação");
        }
    }

    public void existsByCode(String code){
        if(productRepository.existsByCode(code)){
            throw new ProductAlreadyExistsException("Já existe esse produto " +
                    "cadastrado como o código: " + code);
        }
    }
}
