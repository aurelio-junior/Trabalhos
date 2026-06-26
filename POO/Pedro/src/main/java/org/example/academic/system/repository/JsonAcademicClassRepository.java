package org.example.academic.system.repository;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;

/**
 * JSON persistence implementation (US-2374).
 * Delegates reads/writes to the in-memory store; persistAll writes JSON.
 */
public class JsonAcademicClassRepository implements AcademicClassRepository {

    private static final Logger log = LoggerFactory.getLogger(JsonAcademicClassRepository.class);

    private final InMemoryAcademicClassRepository memoryDelegate;

    public JsonAcademicClassRepository(InMemoryAcademicClassRepository memoryDelegate) {
        this.memoryDelegate = memoryDelegate;
    }

    @Override
    public void save(AcademicClass academicClass) {
        memoryDelegate.save(academicClass);
    }

    @Override
    public Optional<AcademicClass> findByCode(String code) {
        return memoryDelegate.findByCode(code);
    }

    @Override
    public List<AcademicClass> findAll() {
        return memoryDelegate.findAll();
    }

    @Override
    public void persistAll(List<AcademicClass> classes) {

        log.info("Persisting {} class(es) to JSON file.", classes.size());

        try (PrintWriter writer = new PrintWriter(new FileWriter("academic-data.json"))) {

            writer.println("{");
            writer.println("  \"classes\": [");

            for (int i = 0; i < classes.size(); i++) {

                AcademicClass academicClass = classes.get(i);

                writer.println("    {");
                writer.println("      \"code\": \"" + academicClass.getCode() + "\",");
                writer.println("      \"title\": \"" + academicClass.getTitle() + "\",");
                writer.println("      \"assessments\": [");

                List<Assessment> assessments = academicClass.getAssessments();

                for (int j = 0; j < assessments.size(); j++) {

                    Assessment assessment = assessments.get(j);

                    writer.println("        {");
                    writer.println("          \"type\": \"" + assessment.getType() + "\",");
                    writer.println("          \"value\": " + assessment.getValue() + ",");
                    writer.println("          \"weight\": " + assessment.getWeight());
                    writer.print("        }");

                    if (j < assessments.size() - 1) {
                        writer.println(",");
                    } else {
                        writer.println();
                    }
                }

                writer.println("      ]");

                writer.print("    }");

                if (i < classes.size() - 1) {
                    writer.println(",");
                } else {
                    writer.println();
                }
            }

            writer.println("  ]");
            writer.println("}");

            log.info("JSON persistence completed successfully.");

        } catch (IOException e) {

            log.error("Failed to persist classes to JSON.", e);

            throw new AcademicSystemException(
                    "Failed to save data to JSON file: " + e.getMessage(),
                    e
            );
        }
    }
}