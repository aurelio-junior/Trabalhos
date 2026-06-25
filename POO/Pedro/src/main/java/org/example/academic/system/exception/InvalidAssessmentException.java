package org.example.academic.system.exception;

/** Thrown when assessment data is invalid (US-2367, US-2361 AC5/AC6). */
public class InvalidAssessmentException extends AcademicSystemException {

    public InvalidAssessmentException(String message) {
        super(message);
    }
}
