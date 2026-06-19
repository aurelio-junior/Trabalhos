package org.example.academic.system.controller;

import org.example.academic.system.model.Role;

import java.util.Scanner;

public class MenuController {

    private final Scanner scanner = new Scanner(System.in);

    public void start(Role role) {

        boolean running = true;

        while (running) {

            try {
                showMenu(role);

                System.out.print("Choose option: ");
                String input = scanner.nextLine();

                int option = Integer.parseInt(input);

                running = handleOption(option, role);

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void showMenu(Role role) {

        System.out.println("\n=== Academic System Menu ===");

        if (role == Role.ADMIN) {
            System.out.println("1 - Register Class");
            System.out.println("2 - Logout");
            System.out.println("0 - Exit");
        }

        if (role == Role.PROFESSOR) {
            System.out.println("1 - Register Assessment");
            System.out.println("2 - Logout");
            System.out.println("0 - Exit");
        }
    }

    private boolean handleOption(int option, Role role) {

        switch (option) {

            case 1:
                System.out.println("Executing role action...");
                break;

            case 2:
                System.out.println("Logging out...");
                return false;

            case 0:
                System.out.println("Exiting system...");
                System.exit(0);
                break;

            default:
                System.out.println("Invalid option.");
        }

        return true;
    }
}