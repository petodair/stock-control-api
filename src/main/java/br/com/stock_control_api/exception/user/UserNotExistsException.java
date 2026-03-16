package br.com.stock_control_api.exception.user;

import br.com.stock_control_api.exception.ResourceNotExistsException;

public class UserNotExistsException extends ResourceNotExistsException {
    public UserNotExistsException(String message) {
        super(message);
    }

}
