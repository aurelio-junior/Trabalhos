package org.example.academic.system.exception;

/** Thrown when the system expects a number but receives invalid input (US-2368 AC1). */
public class InvalidNumericInputException extends KeyboardInputException {

    public InvalidNumericInputException(String input) {
        super("Invalid numeric input: '" + input + "'. Please enter a valid number.");
    }
}
