package org.example.academic.system.model;

import lombok.*;

/**
 * Represents a system user with role-based access.
 * Passwords are never logged (US-2366 AC6).
 */
@Getter
@Setter
@EqualsAndHashCode(of = "username")
public class User {

    public enum Role {
        ADMIN, PROFESSOR
    }

    private final String username;
    private final String password;
    private final Role role;

    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /** Never expose password in toString (AC6 - passwords must not be logged) */
    @Override
    public String toString() {
        return "User{username='" + username + "', role=" + role + "}";
    }
}
