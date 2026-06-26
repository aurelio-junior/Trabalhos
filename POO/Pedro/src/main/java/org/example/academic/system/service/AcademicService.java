package org.example.academic.system.service;

import org.example.academic.system.exception.ClassNotFoundException;
import org.example.academic.system.exception.InvalidAssessmentException;
import org.example.academic.system.model.*;
import org.example.academic.system.repository.AcademicClassRepository;
import org.example.academic.system.validation.DomainValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.example.academic.system.dto.ClassAssessmentReportDTO;
import org.example.academic.system.service.ClassAssessmentReportService;

import java.util.List;
import java.util.Optional;

/**
 * Business logic for managing academic classes and assessments.
 */
public class AcademicService {

    private static final Logger log = LoggerFactory.getLogger(AcademicService.class);
    private final AcademicClassRepository repository;
    private final ClassAssessmentReportService reportService = new ClassAssessmentReportService();

    public AcademicService(AcademicClassRepository repository) {
        this.repository = repository;
    }

    /** Registers a new class after validation (US-2363). */
    public void registerClass(String code, String title) {
        AcademicClass academicClass = new AcademicClass(code, title);
        DomainValidator.validate(academicClass);
        repository.save(academicClass);
        log.info("Class registered: code='{}', title='{}'", code, title);
    }

    /**
     * Registers an assessment in an existing class (US-2361).
     *
     * @param classCode      code of the target class
     * @param assessmentType 1=Exam, 2=PracticalAssignment, 3=Seminar, 4=Assignment
     * @param value          grade value (0-10)
     * @param weight         grade weight (0-1)
     */
    public void registerAssessment(String classCode, int assessmentType, double value, double weight) {
        AcademicClass academicClass = repository.findByCode(classCode)
                .orElseThrow(() -> new ClassNotFoundException(classCode));

        Assessment assessment = createAssessment(assessmentType, value, weight);
        DomainValidator.validate(assessment);
        academicClass.addAssessment(assessment);
        log.info("Assessment '{}' registered in class '{}'", assessment.getType(), classCode);
    }

    private Assessment createAssessment(int type, double value, double weight) {
        return switch (type) {
            case 1 -> new Exam(value, weight);
            case 2 -> new PracticalAssignment(value, weight);
            case 3 -> new Seminar(value, weight);
            case 4 -> new Assignment(value, weight);
            default -> throw new InvalidAssessmentException(
                    "Invalid assessment type: " + type + ". Valid types: 1=Exam, 2=PracticalAssignment, 3=Seminar, 4=Assignment");
        };
    }


    public List<ClassAssessmentReportDTO> generateClassAssessmentReport() {
        return reportService.generate(listAllClasses());
    }


    public Optional<AcademicClass> findByCode(String code) {
        return repository.findByCode(code);
    }

    public List<AcademicClass> listAllClasses() {
        return repository.findAll();
    }

    public void saveAll() {
        repository.persistAll(repository.findAll());
    }
}
