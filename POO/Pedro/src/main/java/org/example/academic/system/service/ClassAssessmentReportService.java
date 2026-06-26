package org.example.academic.system.service;

import org.example.academic.system.dto.ClassAssessmentReportDTO;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;

import java.util.ArrayList;
import java.util.List;

public class ClassAssessmentReportService {

    public List<ClassAssessmentReportDTO> generate(List<AcademicClass> classes) {

        List<ClassAssessmentReportDTO> report = new ArrayList<>();

        if (classes == null || classes.isEmpty()) {
            return report;
        }

        for (AcademicClass c : classes) {

            if (c.getAssessments() == null || c.getAssessments().isEmpty()) {

                report.add(new ClassAssessmentReportDTO(
                        c.getCode(),
                        c.getTitle(),
                        null,
                        null,
                        null
                ));

            } else {

                for (Assessment a : c.getAssessments()) {

                    report.add(new ClassAssessmentReportDTO(
                            c.getCode(),
                            c.getTitle(),
                            a.getType(),
                            a.getValue(),
                            a.getWeight()
                    ));
                }
            }
        }

        return report;
    }
}