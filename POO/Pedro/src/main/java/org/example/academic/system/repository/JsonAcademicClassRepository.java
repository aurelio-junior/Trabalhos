package org.example.academic.system.repository;

import org.example.academic.system.model.AcademicClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * JSON persistence stub (US-2372 AC2).
 * Delegates reads/writes to the in-memory store; persistAll logs intent.
 */
public class JsonAcademicClassRepository implements AcademicClassRepository {

    private static final Logger log = LoggerFactory.getLogger(JsonAcademicClassRepository.class);
    private final InMemoryAcademicClassRepository memoryDelegate;

    public JsonAcademicClassRepository(InMemoryAcademicClassRepository memoryDelegate) {
        this.memoryDelegate = memoryDelegate;
    }

    @Override
    public void save(AcademicClass academicClass) {
        memoryDelegate.save(academicClass);
    }

    @Override
    public Optional<AcademicClass> findByCode(String code) {
        return memoryDelegate.findByCode(code);
    }

    @Override
    public List<AcademicClass> findAll() {
        return memoryDelegate.findAll();
    }

    @Override
    public void persistAll(List<AcademicClass> classes) {
        log.info("JSON persistence selected. Persisting {} class(es) (JSON format not yet fully implemented).", classes.size());
        // JSON serialisation logic would go here
    }
}
