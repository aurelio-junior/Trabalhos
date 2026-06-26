package org.example.academic.system.dto;

public class AssessmentWeightReportDTO {

    private String classCode;
    private String classTitle;

    private Double totalWeight;
    private String status; // VALID / INVALID

    public AssessmentWeightReportDTO(String classCode, String classTitle,
                                     Double totalWeight, String status) {
        this.classCode = classCode;
        this.classTitle = classTitle;
        this.totalWeight = totalWeight;
        this.status = status;
    }

    public String getClassCode() { return classCode; }
    public String getClassTitle() { return classTitle; }
    public Double getTotalWeight() { return totalWeight; }
    public String getStatus() { return status; }
}