package br.com.stock_control_api.validator.product;

import br.com.stock_control_api.dto.product.ProductRequestDTO;

public interface ProductValidator {
    boolean validate(ProductRequestDTO dto);
}
