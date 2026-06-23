package org.example.academic.system.repository;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.logging.PersistenceAuditLogger;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.PersistenceType;
import org.example.academic.system.model.SchoolClass;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TxtClassRepository implements ClassRepository {

    private final List<SchoolClass> database = new ArrayList<>();

    @Override
    public void save(SchoolClass schoolClass) {
        database.add(schoolClass);
    }

    @Override
    public List<SchoolClass> findAll() {
        return database;
    }

    @Override
    public void exportToFile(String fileName) {

        try (FileWriter writer = new FileWriter(fileName)) {

            for (SchoolClass sc : database) {

                writer.write("Class Code: " + sc.getCode() + "\n");
                writer.write("Class Title: " + sc.getTitle() + "\n");

                for (Assessment a : sc.getAssessments()) {
                    writer.write("Assessment: " + a.getClass().getSimpleName()
                            + " | value=" + a.getValue()
                            + " | weight=" + a.getWeight() + "\n");
                }

                writer.write("\n");
            }

        } catch (IOException e) {
            throw new AcademicSystemException("Error generating TXT file: " + e.getMessage());
        }

        PersistenceAuditLogger.saved(PersistenceType.TXT, fileName);
    }
}
