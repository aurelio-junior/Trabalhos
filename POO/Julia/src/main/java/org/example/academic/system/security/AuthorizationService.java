package org.example.academic.system.security;

import org.example.academic.system.exception.AuthorizationException;
import org.example.academic.system.logging.SecurityAuditLogger;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

public class AuthorizationService {

    public boolean isAuthorized(User user, Role requiredRole) {
        return user != null && user.getRole() == requiredRole;
    }

    public void requireRole(User user, Role requiredRole, String operation) {
        if (!isAuthorized(user, requiredRole)) {
            // o log NAO substitui a excecao - registra e mesmo assim lanca (TUS-2392)
            Role role = (user == null) ? null : user.getRole();
            SecurityAuditLogger.authorizationFailure(role, operation);
            throw new AuthorizationException("Access denied for operation: " + operation);
        }
    }
}
