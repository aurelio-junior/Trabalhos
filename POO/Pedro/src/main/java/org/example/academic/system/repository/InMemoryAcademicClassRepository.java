package org.example.academic.system.repository;

import org.example.academic.system.model.AcademicClass;

import java.util.*;

/**
 * In-memory repository — the primary runtime store for academic classes.
 */
public class InMemoryAcademicClassRepository implements AcademicClassRepository {

    private final Map<String, AcademicClass> store = new LinkedHashMap<>();

    @Override
    public void save(AcademicClass academicClass) {
        store.put(academicClass.getCode(), academicClass);
    }

    @Override
    public Optional<AcademicClass> findByCode(String code) {
        return Optional.ofNullable(store.get(code));
    }

    @Override
    public List<AcademicClass> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public void persistAll(List<AcademicClass> classes) {
        // no-op for in-memory; persistence is handled by the TXT/XML/JSON repositories
    }
}
