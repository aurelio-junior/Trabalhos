package org.example.academic.system;

import org.example.academic.system.controller.MenuController;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.Professor;
import org.example.academic.system.model.User;

public class AcademicSystemApplication {

    public static void main(String[] args) {

        // Simulando autenticação (US-2366 depois vai substituir isso)
        User user = new Professor(); // pode trocar para ADMIN depois

        MenuController menu = new MenuController();

        menu.start(user.getRole());
    }
}