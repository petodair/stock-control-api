package br.com.stock_control_api.exception.user;

import br.com.stock_control_api.exception.ResourceConflictException;

public class UserAlreadyExistsException extends ResourceConflictException {
    public UserAlreadyExistsException(String email) {
        super("Já existe um usuário cadastrado com esse email: " + email);
    }
}
