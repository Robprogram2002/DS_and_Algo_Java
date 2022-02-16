package Goodrich_Tamassia.Exceptions;

public class EmptyException extends RuntimeException {
    public EmptyException(String errorMessage) {
        super(errorMessage);
    }
}
