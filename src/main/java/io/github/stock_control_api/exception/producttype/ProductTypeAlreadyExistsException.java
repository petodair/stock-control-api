package io.github.stock_control_api.exception.producttype;

public class ProductTypeAlreadyExistsException extends RuntimeException{
    public ProductTypeAlreadyExistsException(String message){
        super(message);
    }
}
