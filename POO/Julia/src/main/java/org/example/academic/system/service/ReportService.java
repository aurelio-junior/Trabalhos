package org.example.academic.system.service;

import org.example.academic.system.logging.ReportAuditLogger;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.PersistenceType;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.SchoolClass;

public class ReportService {

    public String generateClassAssessmentSummary(SchoolClass schoolClass, Role role) {

        StringBuilder sb = new StringBuilder();
        sb.append("Class Code: ").append(schoolClass.getCode()).append("\n");
        sb.append("Class Title: ").append(schoolClass.getTitle()).append("\n");

        for (Assessment a : schoolClass.getAssessments()) {
            sb.append("Assessment: ").append(a.getClass().getSimpleName())
                    .append(" | value=").append(a.getValue())
                    .append(" | weight=").append(a.getWeight())
                    .append("\n");
        }

        ReportAuditLogger.generated("class assessment summary", role);
        return sb.toString();
    }

    public double generateAssessmentWeightReport(SchoolClass schoolClass, Role role) {

        double total = 0.0;
        for (Assessment a : schoolClass.getAssessments()) {
            total += a.getWeight();
        }

        ReportAuditLogger.generated("assessment weight", role);
        return total;
    }

    public String generatePersistenceConfigurationReport(PersistenceType type, Role role) {
        ReportAuditLogger.generated("persistence configuration", role);
        return "Persistence type: " + type.name() + "\n";
    }
}
