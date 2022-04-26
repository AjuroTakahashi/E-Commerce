package com.ecommerce.exception;

public class StockException extends Exception {

    public StockException() {
        super("-- Pas assez de stock.");
    }
}
