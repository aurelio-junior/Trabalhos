package org.example.academic.system.security;

import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.logging.SecurityAuditLogger;
import org.example.academic.system.model.Administrator;
import org.example.academic.system.model.Professor;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.User;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {

    // pequeno "banco" em memoria so para o sistema rodar/testar
    private record Account(String password, Role role) {
    }

    private final Map<String, Account> accounts = new HashMap<>();

    public AuthenticationService() {
        accounts.put("admin", new Account("admin123", Role.ADMIN));
        accounts.put("professor", new Account("prof123", Role.PROFESSOR));
    }

    public User authenticate(String username, String password) {

        Account account = accounts.get(username);

        if (account == null) {
            SecurityAuditLogger.loginFailure(username);
            throw new AuthenticationException("Invalid username or password");
        }

        if (!account.password().equals(password)) {
            SecurityAuditLogger.loginFailure(username);
            throw new AuthenticationException("Invalid username or password");
        }

        SecurityAuditLogger.loginSuccess(username);
        return userFor(account.role());
    }

    public void logout(String username) {
        SecurityAuditLogger.logout(username);
    }

    private User userFor(Role role) {
        return role == Role.ADMIN ? new Administrator() : new Professor();
    }
}
