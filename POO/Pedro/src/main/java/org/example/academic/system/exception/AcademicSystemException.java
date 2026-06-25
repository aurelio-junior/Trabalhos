package org.example.academic.system.exception;

/**
 * Base exception for all academic domain errors (US-2367).
 */
public class AcademicSystemException extends RuntimeException {

    public AcademicSystemException(String message) {
        super(message);
    }

    public AcademicSystemException(String message, Throwable cause) {
        super(message, cause);
    }
}
