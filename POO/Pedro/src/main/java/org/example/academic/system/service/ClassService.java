package org.example.academic.system.service;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.SchoolClass;
import org.example.academic.system.repository.ClassRepository;

public class ClassService {

    private final ClassRepository repository;

    public ClassService(ClassRepository repository) {
        this.repository = repository;
    }

    public void registerClass(String code, String title) {

        if (code == null || code.isBlank()) {
            throw new AcademicSystemException("Invalid class code");
        }

        if (title == null || title.isBlank()) {
            throw new AcademicSystemException("Invalid class title");
        }

        SchoolClass newClass = new SchoolClass(code);

        repository.save(newClass);
    }
}