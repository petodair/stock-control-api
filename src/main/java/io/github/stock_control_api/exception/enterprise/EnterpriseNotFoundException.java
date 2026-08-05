package io.github.stock_control_api.exception.enterprise;

import io.github.stock_control_api.exception.ResourceNotFoundException;

public class EnterpriseNotFoundException extends ResourceNotFoundException {
    public EnterpriseNotFoundException(String message) {
        super(message);
    }
}
