package io.github.stock_control_api.exception.producttype;

public class ProductTypeAlreadyException extends RuntimeException{
    public ProductTypeAlreadyException(String message){
        super(message);
    }
}
