package io.github.stock_control_api.exception.enterprise;

import io.github.stock_control_api.exception.ResourceAlreadyExistsException;

public class EnterpriseAlreadyExistsException extends ResourceAlreadyExistsException {
    public EnterpriseAlreadyExistsException(String message) {
        super(message);
    }
}
