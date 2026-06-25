package org.example.academic.system.exception;

/** Thrown when academic class data fails validation (US-2367, US-2363 AC3). */
public class InvalidClassException extends AcademicSystemException {

    public InvalidClassException(String message) {
        super(message);
    }
}
