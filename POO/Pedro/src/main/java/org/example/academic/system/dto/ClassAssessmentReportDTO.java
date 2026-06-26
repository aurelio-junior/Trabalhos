package org.example.academic.system.dto;

public class ClassAssessmentReportDTO {

    private String classCode;
    private String classTitle;

    private String assessmentType;
    private Double assessmentValue;
    private Double assessmentWeight;

    public ClassAssessmentReportDTO(String classCode, String classTitle,
                                    String assessmentType,
                                    Double assessmentValue,
                                    Double assessmentWeight) {
        this.classCode = classCode;
        this.classTitle = classTitle;
        this.assessmentType = assessmentType;
        this.assessmentValue = assessmentValue;
        this.assessmentWeight = assessmentWeight;
    }

    public String getClassCode() { return classCode; }
    public String getClassTitle() { return classTitle; }
    public String getAssessmentType() { return assessmentType; }
    public Double getAssessmentValue() { return assessmentValue; }
    public Double getAssessmentWeight() { return assessmentWeight; }
}