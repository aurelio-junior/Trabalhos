package org.example.academic.system.repository;

import org.example.academic.system.model.AcademicClass;

import java.util.List;
import java.util.Optional;

/**
 * Repository abstraction for AcademicClass persistence (TUS-2362 AC5).
 */
public interface AcademicClassRepository {

    void save(AcademicClass academicClass);

    Optional<AcademicClass> findByCode(String code);

    List<AcademicClass> findAll();

    void persistAll(List<AcademicClass> classes);
}
