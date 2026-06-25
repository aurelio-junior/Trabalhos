package org.example.academic.system.exception;

/** Thrown when the user selects an unsupported menu option (US-2368 AC2). */
public class InvalidMenuOptionException extends KeyboardInputException {

    public InvalidMenuOptionException(String option) {
        super("Invalid menu option: '" + option + "'. Please select a valid option.");
    }
}
