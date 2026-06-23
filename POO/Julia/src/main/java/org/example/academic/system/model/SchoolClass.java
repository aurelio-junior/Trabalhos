package org.example.academic.system.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;

public class SchoolClass {

    @NotBlank(message = "class code must not be blank")
    private String code;

    @NotBlank(message = "class title must not be blank")
    private String title;

    @Valid
    private List<Assessment> assessments = new ArrayList<>();

    public SchoolClass(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public void addAssessment(Assessment assessment) {
        this.assessments.add(assessment);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Assessment> assessments) {
        this.assessments = assessments;
    }
}
