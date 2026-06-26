package org.example.academic.system.controller;

import org.example.academic.system.exception.ClassNotFoundException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.PersistenceType;
import org.example.academic.system.model.User;
import org.example.academic.system.security.AuthenticationService;
import org.example.academic.system.service.AcademicService;
import org.example.academic.system.service.PersistenceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.example.academic.system.dto.ClassAssessmentReportDTO;
import org.example.academic.system.dto.AssessmentWeightReportDTO;
import org.example.academic.system.dto.PersistenceConfigReportDTO;

import java.util.List;
import java.util.Optional;

/**
 * Application controller responsible for all academic operations (TUS-2370).
 * Separated from domain model, security layer, and keyboard input layer.
 */
public class AcademicSystemController {

    private static final Logger log = LoggerFactory.getLogger(AcademicSystemController.class);

    private final AcademicService academicService;
    private final AuthenticationService authService;
    private final PersistenceService persistenceService;

    public AcademicSystemController(AcademicService academicService,
                                    AuthenticationService authService,
                                    PersistenceService persistenceService) {
        this.academicService = academicService;
        this.authService = authService;
        this.persistenceService = persistenceService;
    }

    /** Registers a new class. Requires ADMIN role (US-2363 AC5). */
    public void registerClass(String code, String title) {
        authService.requireRole(User.Role.ADMIN);
        academicService.registerClass(code, title);
        log.info("Class registration delegated to service: code='{}', title='{}'", code, title);
    }

    /** Registers an assessment in a class. Requires PROFESSOR or ADMIN role. */
    public void registerAssessment(String classCode, int type, double value, double weight) {
        // Both roles can register assessments; ADMIN can do everything PROFESSOR can
        academicService.registerAssessment(classCode, type, value, weight);
        log.info("Assessment registration delegated to service for class '{}'", classCode);
    }

    /** Persists all classes using the configured persistence mechanism. */
    public void saveData(String txtFilePath) {
        authService.requireRole(User.Role.ADMIN);
        // Rebuild repository with the current persistence type so persistAll uses the right impl
        var repo = persistenceService.buildRepository(txtFilePath);
        repo.persistAll(academicService.listAllClasses());
        log.info("Data persistence delegated (type={})", persistenceService.getCurrentType());
    }

    /** Finds a class by code. */
    public Optional<AcademicClass> findClassByCode(String code) {
        return academicService.findByCode(code);
    }

    /** Lists all registered classes. */
    public List<AcademicClass> listClasses() {
        return academicService.listAllClasses();
    }

    /** Changes the persistence type. Requires ADMIN role (US-2372 AC4). */
    public void configurePersistence(PersistenceType type) {
        authService.requireRole(User.Role.ADMIN);
        persistenceService.setPersistenceType(type);
        log.info("Persistence configuration updated to '{}' by user '{}'",
                type, authService.getCurrentUser().getUsername());
    }


    public List<ClassAssessmentReportDTO> generateClassAssessmentReport() {

        var user = authService.getCurrentUser();

        log.info("Class assessment report generated | user='{}' | role='{}'",
                user.getUsername(),
                user.getRole());

        return academicService.generateClassAssessmentReport();
    }

    public List<AssessmentWeightReportDTO> generateAssessmentWeightReport() {

        var user = authService.getCurrentUser();

        log.info("Assessment weight report generated | user='{}' | role='{}'",
                user.getUsername(),
                user.getRole());

        return academicService.generateAssessmentWeightReport();
    }

    public List<PersistenceConfigReportDTO> generatePersistenceConfigReport() {

        authService.requireRole(User.Role.ADMIN);

        var user = authService.getCurrentUser();

        log.info("Persistence config report generated | user='{}' | role='{}'",
                user.getUsername(),
                user.getRole());

        return academicService.generatePersistenceConfigReport(
                persistenceService.getCurrentType()
        );
    }

    /** Returns the currently configured persistence type (US-2372 AC8). */
    public PersistenceType getCurrentPersistenceType() {
        return persistenceService.getCurrentType();
    }
}
