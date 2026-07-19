package io.github.stock_control_api.exception.product;

import io.github.stock_control_api.exception.ResourceNotFoundException;

public class ProductNotFoundException extends ResourceNotFoundException {
    public ProductNotFoundException(Long id){
        super("produto com o id :" + id + " nao encontrado");
    }

    public ProductNotFoundException(String message){
        super(message);
    }
}
