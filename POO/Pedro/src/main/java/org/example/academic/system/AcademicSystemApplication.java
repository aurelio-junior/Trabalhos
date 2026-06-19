package org.example.academic.system;

import org.example.academic.system.factory.AssessmentFactory;
import org.example.academic.system.model.*;
import org.example.academic.system.repository.TxtClassRepository;

public class AcademicSystemApplication {

    public static void main(String[] args) {

        TxtClassRepository repository = new TxtClassRepository();

        SchoolClass classA = new SchoolClass("POO-01");

        classA.addAssessment(
                AssessmentFactory.create(
                        AssessmentType.EXAM,
                        8.5,
                        0.4
                )
        );

        classA.addAssessment(
                AssessmentFactory.create(
                        AssessmentType.SEMINAR,
                        9.0,
                        0.3
                )
        );

        repository.save(classA);

        // AC1 - gera TXT
        repository.exportToTxt("classes.txt");

        System.out.println("TXT generated successfully!");
    }
}