package org.example.academic.system.controller;

import org.example.academic.system.service.ClassService;

public class ClassController {

    private final ClassService service;

    public ClassController(ClassService service) {
        this.service = service;
    }

    public void registerClass(String code, String title) {
        service.registerClass(code, title);
    }
}