package org.example.academic.system.service;

import org.example.academic.system.model.PersistenceType;
import org.example.academic.system.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Manages the active persistence configuration (US-2372).
 * Allows the administrator to switch between TXT, XML and JSON at runtime.
 */
public class PersistenceService {

    private static final Logger log = LoggerFactory.getLogger(PersistenceService.class);
    private PersistenceType currentType;
    private final InMemoryAcademicClassRepository memoryRepository;

    public PersistenceService(InMemoryAcademicClassRepository memoryRepository) {
        this.memoryRepository = memoryRepository;
        this.currentType = PersistenceType.TXT; // default
    }

    public PersistenceType getCurrentType() {
        return currentType;
    }

    /**
     * Changes the active persistence type (US-2372 AC1).
     */
    public void setPersistenceType(PersistenceType type) {
        this.currentType = type;
        log.info("Persistence type changed to: {}", type);
    }

    /**
     * Returns a configured AcademicClassRepository for the active persistence type.
     */
    public AcademicClassRepository buildRepository(String txtFilePath) {
        return switch (currentType) {
            case TXT  -> new TxtAcademicClassRepository(txtFilePath, memoryRepository);
            case XML  -> new XmlAcademicClassRepository(memoryRepository);
            case JSON -> new JsonAcademicClassRepository(memoryRepository);
        };
    }
}
