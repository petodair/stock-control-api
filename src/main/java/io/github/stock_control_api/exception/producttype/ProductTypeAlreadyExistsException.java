package io.github.stock_control_api.exception.producttype;

import io.github.stock_control_api.exception.ResourceAlreadyExistsException;

public class ProductTypeAlreadyExistsException extends ResourceAlreadyExistsException {
    public ProductTypeAlreadyExistsException(String message){
        super(message);
    }
}
