package org.example.academic.system.repository;

import org.example.academic.system.model.Assessment;
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

    public void exportToTxt(String fileName) {

        try (FileWriter writer = new FileWriter(fileName)) {

            for (SchoolClass sc : database) {

                writer.write("Class Code: " + sc.getCode() + "\n");
                writer.write("Class Title: " + sc.getCode() + "\n"); // não tem title no modelo ainda

                for (Assessment a : sc.getAssessments()) {

                    writer.write("Assessment Type: " + a.getClass().getSimpleName() + "\n");
                    writer.write("Value: " + a.getValue() + "\n");
                    writer.write("Weight: " + a.getWeight() + "\n");
                    writer.write("--------------------------\n");
                }

                writer.write("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException("Error generating TXT file", e);
        }
    }
}