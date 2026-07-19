package io.github.stock_control_api.exception.producttype;

public class ProductTypeNotFoundException extends RuntimeException{
    public ProductTypeNotFoundException(String message){
        super(message);
    }
}
