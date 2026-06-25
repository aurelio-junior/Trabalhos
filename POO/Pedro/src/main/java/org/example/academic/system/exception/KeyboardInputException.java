package org.example.academic.system.exception;

/**
 * Base exception for all keyboard input errors (US-2368).
 * Separated from academic domain and security exceptions.
 */
public class KeyboardInputException extends RuntimeException {

    public KeyboardInputException(String message) {
        super(message);
    }

    public KeyboardInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
