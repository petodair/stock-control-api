package br.com.stock_control_api.exception;

public class IllegalDateException extends RuntimeException {
    public IllegalDateException(String message) {
        super(message);
    }
}
