package io.github.stock_control_api.exception.product;

import io.github.stock_control_api.exception.ResourceAlreadyExistsException;

public class ProductAlreadyExistsException extends ResourceAlreadyExistsException {
    public ProductAlreadyExistsException(String message){
        super(message);
    }
}
