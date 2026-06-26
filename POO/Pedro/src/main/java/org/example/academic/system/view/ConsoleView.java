package org.example.academic.system.view;

import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.exception.*;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.PersistenceType;
import org.example.academic.system.model.User;
import org.example.academic.system.security.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Command-line menu and all keyboard I/O (US-2364).
 * Covered stories: US-2378 (RBAC), US-2379 (Logout), US-2380 (Sequential Menus)
 */
public class ConsoleView {

    private static final Logger log = LoggerFactory.getLogger(ConsoleView.class);
    private static final String TXT_FILE_PATH = "academic-data.txt";

    private final Scanner scanner;
    private final AuthenticationService authService;
    private final AcademicSystemController controller;

    public ConsoleView(AuthenticationService authService, AcademicSystemController controller) {
        this.authService = authService;
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("===========================================");
        System.out.println("       ACADEMIC SYSTEM — Welcome           ");
        System.out.println("===========================================");

        while (true) {
            if (!loginFlow()) {
                System.out.println("Exiting application. Goodbye!");
                break;
            }
            boolean running = true;
            // Loop da sessão autenticada. Se deslogar, sai deste loop e volta para a tela de login (US-2379 AC2)
            while (running && authService.isAuthenticated()) {
                running = menuFlow();
            }
        }
    }

    private boolean loginFlow() {
        System.out.println("\n--- Login ---");
        System.out.print("Username (or 'exit' to quit): ");
        String username = scanner.nextLine().trim();
        if ("exit".equalsIgnoreCase(username)) return false;

        System.out.print("Password: ");
        String password = scanner.nextLine().trim();

        try {
            authService.authenticate(username, password);
            // US-2379 AC3: Garante que o novo usuário logado opere estritamente sob sua própria role
            System.out.println("Welcome, " + authService.getCurrentUser().getUsername() +
                    " [" + authService.getCurrentUser().getRole() + "]");
            return true;
        } catch (AuthenticationException e) {
            System.out.println("[ERROR] " + e.getMessage());
            return loginFlow(); // retry
        }
    }

    private boolean menuFlow() {
        User.Role role = authService.getCurrentUser().getRole();
        if (role == User.Role.ADMIN) {
            return adminMenu();
        } else {
            return professorMenu();
        }
    }

    // US-2380 AC1: Menu do ADMIN sequencial iniciando em 1
    private boolean adminMenu() {
        System.out.println("\n========== ADMIN MENU ==========");
        System.out.println("1. Register Class");
        System.out.println("2. Register Assessment");
        System.out.println("3. List Classes");
        System.out.println("4. Search Class by Code");
        System.out.println("5. Save Data");
        System.out.println("6. Configure Persistence Type");
        System.out.println("7. Persistence Configuration Report");
        System.out.println("8. Class Assessment Summary Report (US-2375)");
        System.out.println("9. Logout");
        System.out.println("10. Exit");
        System.out.print("Choose an option: ");

        String input = scanner.nextLine().trim();
        try {
            int option = parseInteger(input);
            switch (option) {
                case 1 -> registerClassFlow();
                case 2 -> registerAssessmentFlow();
                case 3 -> listClassesFlow();
                case 4 -> searchClassFlow();
                case 5 -> saveDataFlow();
                case 6 -> configurePersistenceFlow();
                case 7 -> persistenceReportFlow();
                case 8 -> classAssessmentSummaryReportFlow();
                case 9 -> { executeLogout(); return true; } // US-2379 AC1
                case 10 -> { return false; }
                default -> throw new InvalidMenuOptionException(input);
            }
        } catch (AcademicSystemException | InvalidMenuOptionException | InvalidNumericInputException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }
        return true;
    }

    // US-2380 AC2: Menu do PROFESSOR sequencial iniciando em 1
    // US-2380 AC3: O mesmo número (ex: 1) representa uma operação diferente aqui (Register Assessment)
    private boolean professorMenu() {
        System.out.println("\n========== PROFESSOR MENU ==========");
        System.out.println("1. Register Assessment");
        System.out.println("2. List Classes");
        System.out.println("3. Search Class by Code");
        System.out.println("4. Class Assessment Summary Report (US-2375)");
        System.out.println("5. Logout");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");

        String input = scanner.nextLine().trim();
        try {
            int option = parseInteger(input);

            // US-2380 AC6: Garante que qualquer opção fora do escopo sequencial do professor seja barrada
            if (option < 1 || option > 6) {
                throw new InvalidMenuOptionException(input);
            }

            switch (option) {
                case 1 -> registerAssessmentFlow(); // Executa o fluxo legítimo do professor
                case 2 -> listClassesFlow();
                case 3 -> searchClassFlow();
                case 4 -> classAssessmentSummaryReportFlow();
                case 5 -> { executeLogout(); return true; }
                case 6 -> { return false; }
                default -> throw new InvalidMenuOptionException(input);
            }
        } catch (InvalidMenuOptionException | InvalidNumericInputException e) {
            System.out.println("[INPUT ERROR] " + e.getMessage());
            log.warn("Keyboard input error: {}", e.getMessage());
        } catch (AcademicSystemException e) {
            System.out.println("[ACADEMIC ERROR] " + e.getMessage());
            log.error("Academic error: {}", e.getMessage());
        } catch (AuthorizationException e) {
            System.out.println("[ACCESS DENIED] " + e.getMessage());
            log.warn("Authorization error: {}", e.getMessage());
        }
        return true;
    }

    /**
     * Centraliza a lógica de Logout atendendo a US-2379
     */
    private void executeLogout() {
        String username = authService.getCurrentUser().getUsername();
        User.Role role = authService.getCurrentUser().getRole();

        // Executa a limpeza da sessão no AuthenticationService (US-2379 AC1, AC6, AC7)
        authService.logout();

        System.out.println("Logout successful. Current session terminated.");

        // US-2379 AC4: Log de Auditoria obrigatório para o Logout
        log.info("Audit: user '{}' with role '{}' logged out successfully.", username, role);
    }

    // -----------------------------------------------------------------------
    // Operations (Mantidas inalteradas - US-2379 AC5 / US-2380 AC7)
    // -----------------------------------------------------------------------

    private void classAssessmentSummaryReportFlow() {
        System.out.println("\n--- Class Assessment Summary Report ---");
        controller.generateClassAssessmentSummaryReport();
    }

    private void registerClassFlow() {
        System.out.println("\n--- Register Class ---");
        System.out.print("Class code (e.g. CS101): ");
        String code = scanner.nextLine().trim();
        System.out.print("Class title: ");
        String title = scanner.nextLine().trim();
        controller.registerClass(code, title);
        System.out.println("Class '" + code + "' registered successfully.");
        log.info("Audit: class '{}' registered by '{}'", code, authService.getCurrentUser().getUsername());
    }

    private void registerAssessmentFlow() {
        System.out.println("\n--- Register Assessment ---");
        System.out.print("Class code: ");
        String code = scanner.nextLine().trim();

        System.out.println("Assessment type:");
        System.out.println("  1 - Exam\n  2 - PracticalAssignment\n  3 - Seminar\n  4 - Assignment");
        System.out.print("Select type: ");
        int type = parseInteger(scanner.nextLine().trim());

        System.out.print("Value (0.0 - 10.0): ");
        double value = parseDouble(scanner.nextLine().trim());

        System.out.print("Weight (0.0 - 1.0): ");
        double weight = parseDouble(scanner.nextLine().trim());

        controller.registerAssessment(code, type, value, weight);
        System.out.println("Assessment registered successfully in class '" + code + "'.");
    }

    private void listClassesFlow() {
        System.out.println("\n--- Registered Classes ---");
        List<AcademicClass> classes = controller.listClasses();
        if (classes.isEmpty()) {
            System.out.println("No classes registered.");
            return;
        }
        for (AcademicClass cls : classes) {
            System.out.printf("  [%s] %s%n", cls.getCode(), cls.getTitle());
            for (Assessment a : cls.getAssessments()) {
                System.out.printf("      %-22s value=%.1f  weight=%.2f%n", a.getType(), a.getValue(), a.getWeight());
            }
        }
    }

    private void searchClassFlow() {
        System.out.println("\n--- Search Class ---");
        System.out.print("Class code: ");
        String code = scanner.nextLine().trim();
        Optional<AcademicClass> result = controller.findClassByCode(code);
        if (result.isPresent()) {
            AcademicClass cls = result.get();
            System.out.printf("Found: [%s] %s%n", cls.getCode(), cls.getTitle());
            for (Assessment a : cls.getAssessments()) {
                System.out.printf("  %-22s value=%.1f  weight=%.2f%n", a.getType(), a.getValue(), a.getWeight());
            }
        } else {
            System.out.println("Class not found: " + code);
        }
    }

    private void saveDataFlow() {
        controller.saveData(TXT_FILE_PATH);
        System.out.println("Data saved successfully (type=" + controller.getCurrentPersistenceType() + ").");
        log.info("Audit: data saved by '{}'", authService.getCurrentUser().getUsername());
    }

    private void configurePersistenceFlow() {
        System.out.println("\n--- Configure Persistence Type ---");
        System.out.println("  1 - TXT\n  2 - XML\n  3 - JSON");
        System.out.print("Select type: ");
        int option = parseInteger(scanner.nextLine().trim());
        PersistenceType type = switch (option) {
            case 1 -> PersistenceType.TXT;
            case 2 -> PersistenceType.XML;
            case 3 -> PersistenceType.JSON;
            default -> throw new InvalidMenuOptionException(String.valueOf(option));
        };
        controller.configurePersistence(type);
        System.out.println("Persistence type configured to: " + type);
        log.info("Audit: persistence type set to '{}' by '{}'", type, authService.getCurrentUser().getUsername());
    }

    private void persistenceReportFlow() {
        System.out.println("\n--- Persistence Configuration Report ---");
        System.out.println("Current persistence type: " + controller.getCurrentPersistenceType());
    }

    private int parseInteger(String input) {
        try { return Integer.parseInt(input); } catch (NumberFormatException e) { throw new InvalidNumericInputException(input); }
    }

    private double parseDouble(String input) {
        try { return Double.parseDouble(input); } catch (NumberFormatException e) { throw new InvalidNumericInputException(input); }
    }
}