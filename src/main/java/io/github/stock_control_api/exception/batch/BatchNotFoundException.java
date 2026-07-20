package io.github.stock_control_api.exception.batch;

import io.github.stock_control_api.exception.ResourceNotFoundException;

public class BatchNotFoundException extends ResourceNotFoundException {
    public BatchNotFoundException(String message) {
        super(message);
    }
}
