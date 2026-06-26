package org.example.academic.system.dto;

public class PersistenceConfigReportDTO {

    private String persistenceType;

    public PersistenceConfigReportDTO(String persistenceType) {
        this.persistenceType = persistenceType;
    }

    public String getPersistenceType() {
        return persistenceType;
    }
}