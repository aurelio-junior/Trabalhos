package org.example.academic.system;

import org.example.academic.system.factory.AssessmentFactory;
import org.example.academic.system.model.*;
import org.example.academic.system.exception.AcademicSystemException;

import java.util.HashMap;
import java.util.Map;

public class AcademicSystemApplication {

    public static void main(String[] args) {

        // Simulando banco em memória (AC4)
        Map<String, SchoolClass> classes = new HashMap<>();

        SchoolClass classA = new SchoolClass("POO-01");
        classes.put(classA.getCode(), classA);

        User professor = new Professor();

        // ===== US-2361 TESTE =====
        try {

            String classCode = "POO-01";

            SchoolClass schoolClass = classes.get(classCode);

            if (schoolClass == null) {
                throw new AcademicSystemException("Class not found");
            }

            if (professor.getRole() != Role.PROFESSOR) {
                throw new AcademicSystemException("Access denied");
            }

            Assessment assessment = AssessmentFactory.create(
                    AssessmentType.EXAM,
                    8.5,
                    0.4
            );

            schoolClass.addAssessment(assessment);

            System.out.println("Assessment registered successfully!");
            System.out.println("Total assessments: "
                    + schoolClass.getAssessments().size());

        } catch (AcademicSystemException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}