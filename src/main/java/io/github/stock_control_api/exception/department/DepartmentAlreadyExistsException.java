package io.github.stock_control_api.exception.department;

import io.github.stock_control_api.exception.ResourceAlreadyExistsException;

public class DepartmentAlreadyExistsException extends ResourceAlreadyExistsException {
    public DepartmentAlreadyExistsException(String message) {
        super(message);
    }
}
