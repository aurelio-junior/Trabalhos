package org.example.academic.system.repository;

import org.example.academic.system.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Loads users from a TXT file (US-2366 AC5).
 * Format: username|password|ROLE
 * Falls back to a set of default users if the file does not exist.
 */
public class TxtUserRepository {

    private static final Logger log = LoggerFactory.getLogger(TxtUserRepository.class);
    private final List<User> users = new ArrayList<>();

    public TxtUserRepository(String filePath) {
        loadUsers(filePath);
    }

    private void loadUsers(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            log.warn("User file '{}' not found. Loading default users.", filePath);
            users.add(new User("admin", "admin123", User.Role.ADMIN));
            users.add(new User("professor", "prof123", User.Role.PROFESSOR));
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    users.add(new User(parts[0], parts[1], User.Role.valueOf(parts[2].toUpperCase())));
                }
            }
            log.info("Loaded {} user(s) from '{}'.", users.size(), filePath);
        } catch (IOException e) {
            log.error("Failed to read user file: {}", filePath, e);
        }
    }

    public Optional<User> findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }
}
