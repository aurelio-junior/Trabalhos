package org.example.academic.system.service;

import org.example.academic.system.dto.AssessmentWeightReportDTO;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

import java.util.ArrayList;
import java.util.List;

public class AssessmentWeightReportService {

    public List<AssessmentWeightReportDTO> generate(List<AcademicClass> classes) {

        List<AssessmentWeightReportDTO> report = new ArrayList<>();

        if (classes == null || classes.isEmpty()) {
            return report;
        }

        for (AcademicClass c : classes) {

            double totalWeight = 0.0;

            if (c.getAssessments() != null) {
                for (Assessment a : c.getAssessments()) {
                    totalWeight += a.getWeight();
                }
            }

            String status = (totalWeight == 1.0) ? "VALID" : "INVALID";

            report.add(new AssessmentWeightReportDTO(
                    c.getCode(),
                    c.getTitle(),
                    totalWeight,
                    status
            ));
        }

        return report;
    }
}