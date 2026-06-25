package org.example.academic.system.security;

import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.exception.AuthorizationException;
import org.example.academic.system.model.User;
import org.example.academic.system.repository.TxtUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * Handles authentication and role-based authorisation (US-2366, US-2369).
 * Kept completely separated from academic domain logic (US-2366 AC8).
 */
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private final TxtUserRepository userRepository;
    private User currentUser;

    public AuthenticationService(TxtUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates a user. Throws AuthenticationException on failure.
     * Passwords are never logged (US-2366 AC6).
     */
    public User authenticate(String username, String password) {
        log.info("Authentication attempt for username: '{}'", username);
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isEmpty() || !found.get().getPassword().equals(password)) {
            log.warn("Authentication failed for username: '{}'", username);
            throw new AuthenticationException();
        }
        currentUser = found.get();
        log.info("Authentication successful: {}", currentUser);
        return currentUser;
    }

    /** Logs out the current user. */
    public void logout() {
        if (currentUser != null) {
            log.info("User '{}' logged out.", currentUser.getUsername());
        }
        currentUser = null;
    }

    /** Returns the currently authenticated user, or null. */
    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isAuthenticated() {
        return currentUser != null;
    }

    /**
     * Checks that the current user has the required role.
     * Throws AuthorizationException if not (US-2369 AC2).
     */
    public void requireRole(User.Role requiredRole) {
        if (currentUser == null) {
            throw new AuthorizationException(requiredRole.name());
        }
        if (currentUser.getRole() != requiredRole) {
            log.warn("Authorization denied. User '{}' has role [{}], required [{}].",
                    currentUser.getUsername(), currentUser.getRole(), requiredRole);
            throw new AuthorizationException(requiredRole.name());
        }
    }
}
