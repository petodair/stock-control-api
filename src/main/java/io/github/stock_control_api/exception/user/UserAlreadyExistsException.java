package io.github.stock_control_api.exception.user;

import io.github.stock_control_api.exception.ResourceAlreadyExistsException;

public class UserAlreadyExistsException extends ResourceAlreadyExistsException {
    public UserAlreadyExistsException(String message){
        super(message);
    }
}
