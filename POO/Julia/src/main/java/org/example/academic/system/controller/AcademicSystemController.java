package org.example.academic.system.controller;

import org.example.academic.system.service.ClassService;

public class AcademicSystemController {

    private final ClassService classService;

    public AcademicSystemController(ClassService classService) {
        this.classService = classService;
    }

    public void registerClass(String code, String title) {
        // delega pro service - o controller nao guarda mais regra de cadastro
        classService.registerClass(code, title);
    }
}
