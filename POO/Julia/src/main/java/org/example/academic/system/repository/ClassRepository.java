package org.example.academic.system.repository;

import org.example.academic.system.model.SchoolClass;

import java.util.List;

public interface ClassRepository {

    void save(SchoolClass schoolClass);

    List<SchoolClass> findAll();

    void exportToFile(String fileName);
}
