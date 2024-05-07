package dev.mmieckowski;

public class InvalidPlayerSymbolException extends RuntimeException {
    public InvalidPlayerSymbolException(String msg) {
        super(msg);
    }
}
