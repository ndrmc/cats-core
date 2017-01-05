package org.cats.stock.receipt.error;

/**
 * Created by alexander on 1/4/17.
 */
public class ReceiptServiceException extends RuntimeException {
    public ReceiptServiceException(String  message ) {
        super(message);
    }
}
