package org.example.academic.system;

import org.example.academic.system.repository.InMemoryAcademicClassRepository;
import org.example.academic.system.repository.TxtUserRepository;
import org.example.academic.system.security.AuthenticationService;
import org.example.academic.system.service.AcademicService;
import org.example.academic.system.service.PersistenceService;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.view.ConsoleView;

/**
 * Root application class that wires all dependencies and starts the CLI.
 */
public class AcademicSystem {

    public static void run() {
        // Infrastructure
        InMemoryAcademicClassRepository memoryRepo = new InMemoryAcademicClassRepository();
        TxtUserRepository userRepository = new TxtUserRepository("users.txt");

        // Services
        PersistenceService persistenceService = new PersistenceService(memoryRepo);
        AcademicService academicService = new AcademicService(memoryRepo);
        AuthenticationService authService = new AuthenticationService(userRepository);

        // Controller (TUS-2370)
        AcademicSystemController controller = new AcademicSystemController(
                academicService, authService, persistenceService);

        // View — only responsible for startup, auth, menus, and I/O (TUS-2370)
        ConsoleView view = new ConsoleView(authService, controller);
        view.start();
    }
}
