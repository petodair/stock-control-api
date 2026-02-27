package br.com.stock_control_api.exception.product;

import br.com.stock_control_api.exception.ResourceConflictException;

public class ProductCodeAlreadyExistsException extends ResourceConflictException {
    public ProductCodeAlreadyExistsException(String code) {
        super("Já existe um produto cadastrado com o código: " + code);
    }
}
