package exceptions;

public class StockException extends Exception {

    public StockException() {
        super("-- Pas assez de stock.");
    }
}
