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

public class XmlClassRepository implements ClassRepository {

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

            writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            writer.write("<classes>\n");

            for (SchoolClass sc : database) {
                writer.write("  <class code=\"" + escape(sc.getCode())
                        + "\" title=\"" + escape(sc.getTitle()) + "\">\n");

                for (Assessment a : sc.getAssessments()) {
                    writer.write("    <assessment type=\"" + a.getClass().getSimpleName()
                            + "\" value=\"" + a.getValue()
                            + "\" weight=\"" + a.getWeight() + "\"/>\n");
                }

                writer.write("  </class>\n");
            }

            writer.write("</classes>\n");

        } catch (IOException e) {
            throw new AcademicSystemException("Error generating XML file: " + e.getMessage());
        }

        PersistenceAuditLogger.saved(PersistenceType.XML, fileName);
    }

    private String escape(String value) {
        if (value == null) {
            return "";
        }
        return value.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
