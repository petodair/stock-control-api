package br.com.stock_control_api.exception.batch;

public class BatchNumberAlreadyExists extends RuntimeException {
    public BatchNumberAlreadyExists(String batchNumber) {
        super("O lote de número " + batchNumber + " já está cadastrado no sistema.");
    }
}
