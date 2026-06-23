package org.example.academic.system;

import org.example.academic.system.model.Exam;
import org.example.academic.system.model.PersistenceType;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.SchoolClass;
import org.example.academic.system.model.Seminar;
import org.example.academic.system.service.ReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("US-2388 - Geração de relatórios")
class ReportGenerationTest {

    private ReportService reportService;
    private SchoolClass schoolClass;

    @BeforeEach
    void setUp() {
        reportService = new ReportService();
        schoolClass = new SchoolClass("CC101", "Introdução a POO");
        schoolClass.addAssessment(new Exam(8.0, 0.4));
        schoolClass.addAssessment(new Seminar(9.0, 0.6));
    }

    @Test
    @DisplayName("resumo traz código, título, tipo, valor e peso")
    void summaryShouldContainClassData() {
        String report = reportService.generateClassAssessmentSummary(schoolClass, Role.PROFESSOR);

        assertTrue(report.contains("CC101"));
        assertTrue(report.contains("Introdução a POO"));
        assertTrue(report.contains("Exam"));
        assertTrue(report.contains("8.0"));
        assertTrue(report.contains("0.4"));
    }

    @Test
    @DisplayName("relatório de pesos soma o peso total corretamente")
    void weightReportShouldCalculateTotal() {
        double total = reportService.generateAssessmentWeightReport(schoolClass, Role.PROFESSOR);
        assertEquals(1.0, total, 1e-9);
    }

    @Test
    @DisplayName("relatório de configuração mostra o tipo de persistência")
    void persistenceConfigReportShouldShowType() {
        String report = reportService.generatePersistenceConfigurationReport(PersistenceType.JSON, Role.ADMIN);
        assertTrue(report.contains("JSON"));
    }
}
