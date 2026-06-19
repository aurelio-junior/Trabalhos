package org.example.academic.system;

import org.example.academic.system.controller.ClassController;
import org.example.academic.system.repository.TxtClassRepository;
import org.example.academic.system.service.ClassService;

import java.util.Scanner;

public class AcademicSystemApplication {

    public static void main(String[] args) {

        TxtClassRepository repository = new TxtClassRepository();
        ClassService service = new ClassService(repository);
        ClassController controller = new ClassController(service);

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Register Class ===");

        try {

            System.out.print("Enter class code: ");
            String code = scanner.nextLine();

            System.out.print("Enter class title: ");
            String title = scanner.nextLine();

            controller.registerClass(code, title);

            System.out.println("Class registered successfully!");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}