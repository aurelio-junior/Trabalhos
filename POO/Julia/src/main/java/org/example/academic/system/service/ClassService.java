package org.example.academic.system.service;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.logging.AppLogger;
import org.example.academic.system.model.SchoolClass;
import org.example.academic.system.repository.ClassRepository;

import java.util.List;
import java.util.logging.Logger;

/**
 * TUS-2396 - ClassService.
 * Concentra a logica de cadastro de turma; o controller so delega pra ca.
 */
public class ClassService {

    private static final Logger log = AppLogger.get(ClassService.class);

    private final ClassRepository repository;

    public ClassService(ClassRepository repository) {
        this.repository = repository;
    }

    public SchoolClass registerClass(String code, String title) {

        if (code == null || code.isBlank()) {
            throw new AcademicSystemException("Invalid class code");
        }

        if (title == null || title.isBlank()) {
            throw new AcademicSystemException("Invalid class title");
        }

        SchoolClass schoolClass = new SchoolClass(code, title);
        repository.save(schoolClass);

        log.info("Class registered: " + code);
        return schoolClass;
    }

    public List<SchoolClass> listClasses() {
        return repository.findAll();
    }
}
