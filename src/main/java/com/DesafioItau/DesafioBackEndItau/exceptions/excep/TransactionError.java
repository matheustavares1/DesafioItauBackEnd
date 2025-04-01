package com.DesafioItau.DesafioBackEndItau.exceptions.excep;

public class TransactionError extends RuntimeException {
    public TransactionError(String message) {
        super(message);
    }
}
