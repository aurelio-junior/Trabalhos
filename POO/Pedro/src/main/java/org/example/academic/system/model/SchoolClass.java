package org.example.academic.system.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SchoolClass {

    private String code;
    private String title;
    private List<Assessment> assessments = new ArrayList<>();

    public SchoolClass(String code) {
        this.code = code;
    }

    public void addAssessment(Assessment assessment) {
        this.assessments.add(assessment);
    }
}