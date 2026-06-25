package org.example.academic.system.exception;

/**
 * Base exception for all security-related errors (US-2369).
 * Separated from academic domain and keyboard input exceptions.
 */
public class SecurityException extends RuntimeException {

    public SecurityException(String message) {
        super(message);
    }
}
