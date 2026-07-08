package io.github.stock_control_api.exception.product;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(Long id){
        super("produto com o id :" + id + " nao encontrado");
    }

    public ProductNotFoundException(String message){
        super(message);
    }
}
