package org.example.academic.system.exception;

/** Thrown when a class code is not found in the academic system (US-2367, US-2361 AC4). */
public class ClassNotFoundException extends AcademicSystemException {

    public ClassNotFoundException(String code) {
        super("Academic class not found with code: " + code);
    }
}
