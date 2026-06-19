package org.example.academic.system.factory;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.*;

public class AssessmentFactory {

    public static Assessment create(
            AssessmentType type,
            double value,
            double weight) {

        if (type == null) {
            throw new AcademicSystemException("Invalid assessment type");
        }

        return switch (type) {
            case EXAM -> new Sminar(value, weight);
            case PRACTICAL_ASSIGNMENT -> new PracticalAssignment(value, weight);
            case SEMINAR -> new Seminar(value, weight);
            case ASSIGNMENT -> new Assignment(value, weight);
        };
    }
}