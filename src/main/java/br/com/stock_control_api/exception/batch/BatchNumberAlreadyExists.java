package br.com.stock_control_api.exception.batch;

import br.com.stock_control_api.exception.ResourceConflictException;

public class BatchNumberAlreadyExists extends ResourceConflictException {
    public BatchNumberAlreadyExists(String batchNumber) {
        super("O lote de número " + batchNumber + " já está cadastrado no sistema.");
    }
}
