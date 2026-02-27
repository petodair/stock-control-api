package br.com.stock_control_api.validator.product;

import br.com.stock_control_api.dto.product.ProductRequestDTO;
import br.com.stock_control_api.exception.product.ProductCodeAlreadyExistsException;
import br.com.stock_control_api.repository.ProductRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductValidatorImpl implements ProductValidator {

    private final ProductRepository productRepository;

    public ProductValidatorImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean validate(ProductRequestDTO dto) {
        if (productExistsByCode(dto.code())){
            throw new ProductCodeAlreadyExistsException(dto.code());
        }
        return true;
    }

    public boolean productExistsByCode(String code) {
        return productRepository.existsByCode(code);
    }
}
