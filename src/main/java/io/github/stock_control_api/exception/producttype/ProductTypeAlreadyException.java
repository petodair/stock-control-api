package io.github.stock_control_api.exception.producttype;

import io.github.stock_control_api.entity.Product;

public class ProductTypeAlreadyException extends RuntimeException{
    public ProductTypeAlreadyException(String message){
        super(message);
    }
}
