package org.example.academic.system.model;

import java.util.ArrayList;
import java.util.List;

import org.example.academic.system.exception.AcademicSystemException;

public class SchoolClass {

    private final String code;
    private final List<Assessment> assessments = new ArrayList<>();

    public SchoolClass(String code) {
        if (code == null || code.isBlank()) {
            throw new AcademicSystemException("Invalid class code");
        }
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void addAssessment(Assessment assessment) {

        if (assessment == null) {
            throw new AcademicSystemException("Invalid assessment");
        }

        assessments.add(assessment);
    }
}