package Goodrich_Tamassia.Exceptions;

import java.util.NoSuchElementException;

public class EmptyException extends NoSuchElementException {
    public EmptyException(String errorMessage) {
        super(errorMessage);
    }
}
