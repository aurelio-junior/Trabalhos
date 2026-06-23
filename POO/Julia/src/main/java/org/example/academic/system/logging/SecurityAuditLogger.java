package org.example.academic.system.logging;

import org.example.academic.system.model.Role;

import java.util.logging.Logger;

/**
 * TUS-2391 / TUS-2392 - log de eventos de segurança.
 * Importante: a senha NUNCA é registrada, só o usuário/perfil.
 */
public final class SecurityAuditLogger {

    private static final Logger log = AppLogger.get(SecurityAuditLogger.class);

    private SecurityAuditLogger() {
    }

    public static void loginSuccess(String username) {
        log.info("Login succeeded for user '" + safe(username) + "'");
    }

    public static void loginFailure(String username) {
        log.warning("Login failed for user '" + safe(username) + "'");
    }

    public static void logout(String username) {
        log.info("Logout for user '" + safe(username) + "'");
    }

    public static void authorizationFailure(Role role, String operation) {
        String r = (role == null) ? "UNKNOWN" : role.name();
        log.warning("Authorization denied for role " + r + " on operation '" + operation + "'");
    }

    private static String safe(String username) {
        return (username == null || username.isBlank()) ? "unknown" : username;
    }
}
