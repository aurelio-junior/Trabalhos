package org.example.academic.system.exception;

/** Thrown when authentication fails due to invalid credentials (US-2369 AC1). */
public class AuthenticationException extends SecurityException {

    public AuthenticationException() {
        super("Authentication failed: invalid username or password.");
    }
}
