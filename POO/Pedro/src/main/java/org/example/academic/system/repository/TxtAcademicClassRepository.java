package org.example.academic.system.repository;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Optional;

/**
 * TXT file-based persistence for academic classes and their assessments (TUS-2362).
 *
 * File format per line:
 *   classCode|classTitle|assessmentType|assessmentValue|assessmentWeight
 * A class with no assessments is written as:
 *   classCode|classTitle|||
 */
public class TxtAcademicClassRepository implements AcademicClassRepository {

    private static final Logger log = LoggerFactory.getLogger(TxtAcademicClassRepository.class);
    private static final String SEPARATOR = "|";

    private final String filePath;
    private final InMemoryAcademicClassRepository memoryDelegate;

    public TxtAcademicClassRepository(String filePath, InMemoryAcademicClassRepository memoryDelegate) {
        this.filePath = filePath;
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

    /**
     * Persists all classes and their assessments to the TXT file (TUS-2362 AC1-AC4).
     */
    @Override
    public void persistAll(List<AcademicClass> classes) {
        log.info("Persisting {} class(es) to TXT file: {}", classes.size(), filePath);
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath, false))) {
            for (AcademicClass cls : classes) {
                if (cls.getAssessments().isEmpty()) {
                    writer.println(cls.getCode() + SEPARATOR + cls.getTitle() + SEPARATOR + SEPARATOR + SEPARATOR);
                } else {
                    for (Assessment a : cls.getAssessments()) {
                        writer.println(
                                cls.getCode() + SEPARATOR +
                                cls.getTitle() + SEPARATOR +
                                a.getType() + SEPARATOR +
                                a.getValue() + SEPARATOR +
                                a.getWeight()
                        );
                    }
                }
            }
            log.info("TXT persistence completed successfully.");
        } catch (IOException e) {
            log.error("Failed to persist classes to TXT file: {}", filePath, e);
            throw new AcademicSystemException("Failed to save data to TXT file: " + e.getMessage(), e);
        }
    }
}
