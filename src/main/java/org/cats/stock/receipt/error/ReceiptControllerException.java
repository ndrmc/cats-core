package org.cats.stock.receipt.error;

/**
 * Created by alexander on 1/5/17.
 */
public class ReceiptControllerException extends RuntimeException {
    public ReceiptControllerException(String message) {
        super(message);
    }

    public ReceiptControllerException(String message, Throwable cause) {
        super(message, cause);
    }
}
