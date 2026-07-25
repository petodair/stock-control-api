package io.github.stock_control_api.exception.user;

import io.github.stock_control_api.exception.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException(String message){
        super(message);
    }
}
