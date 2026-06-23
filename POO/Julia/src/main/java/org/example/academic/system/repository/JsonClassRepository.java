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

public class JsonClassRepository implements ClassRepository {

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

            writer.write("[\n");

            for (int i = 0; i < database.size(); i++) {
                SchoolClass sc = database.get(i);

                writer.write("  {\n");
                writer.write("    \"code\": \"" + sc.getCode() + "\",\n");
                writer.write("    \"title\": \"" + sc.getTitle() + "\",\n");
                writer.write("    \"assessments\": [\n");

                List<Assessment> list = sc.getAssessments();
                for (int j = 0; j < list.size(); j++) {
                    Assessment a = list.get(j);
                    writer.write("      {\"type\": \"" + a.getClass().getSimpleName()
                            + "\", \"value\": " + a.getValue()
                            + ", \"weight\": " + a.getWeight() + "}");
                    writer.write(j < list.size() - 1 ? ",\n" : "\n");
                }

                writer.write("    ]\n");
                writer.write("  }");
                writer.write(i < database.size() - 1 ? ",\n" : "\n");
            }

            writer.write("]\n");

        } catch (IOException e) {
            throw new AcademicSystemException("Error generating JSON file: " + e.getMessage());
        }

        PersistenceAuditLogger.saved(PersistenceType.JSON, fileName);
    }
}
