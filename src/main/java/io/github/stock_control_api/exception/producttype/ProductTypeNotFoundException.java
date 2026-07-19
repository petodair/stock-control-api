package io.github.stock_control_api.exception.producttype;

import io.github.stock_control_api.exception.ResourceNotFoundException;

public class ProductTypeNotFoundException extends ResourceNotFoundException {
    public ProductTypeNotFoundException(String message){
        super(message);
    }
}
