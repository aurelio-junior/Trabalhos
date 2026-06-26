package org.example.academic.system.service;

import org.example.academic.system.dto.PersistenceConfigReportDTO;
import org.example.academic.system.model.PersistenceType;

import java.util.List;
import java.util.Collections;

public class PersistenceConfigReportService {

    public List<PersistenceConfigReportDTO> generate(PersistenceType type) {

        if (type == null) {
            return Collections.emptyList();
        }

        return List.of(
                new PersistenceConfigReportDTO(type.name())
        );
    }
}