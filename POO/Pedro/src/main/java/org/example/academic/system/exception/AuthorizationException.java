package org.example.academic.system.exception;

/** Thrown when an authenticated user attempts an operation outside their role (US-2369 AC2). */
public class AuthorizationException extends SecurityException {

    public AuthorizationException(String requiredRole) {
        super("Access denied: operation requires role [" + requiredRole + "].");
    }
}
