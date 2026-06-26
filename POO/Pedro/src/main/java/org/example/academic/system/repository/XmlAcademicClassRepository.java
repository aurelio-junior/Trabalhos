package org.example.academic.system.repository;

import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Optional;

/**
 * XML persistence stub (US-2372 AC2).
 * Delegates reads/writes to the in-memory store; persistAll logs intent.
 */
public class XmlAcademicClassRepository implements AcademicClassRepository {

    private static final Logger log = LoggerFactory.getLogger(XmlAcademicClassRepository.class);

    private final InMemoryAcademicClassRepository memoryDelegate;

    public XmlAcademicClassRepository(InMemoryAcademicClassRepository memoryDelegate) {
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

        log.info("Persisting {} class(es) to XML file.", classes.size());

        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element root = document.createElement("academicData");
            document.appendChild(root);

            for (AcademicClass academicClass : classes) {

                Element classElement = document.createElement("class");
                root.appendChild(classElement);

                Element code = document.createElement("code");
                code.setTextContent(academicClass.getCode());
                classElement.appendChild(code);

                Element title = document.createElement("title");
                title.setTextContent(academicClass.getTitle());
                classElement.appendChild(title);

                Element assessmentsElement = document.createElement("assessments");
                classElement.appendChild(assessmentsElement);

                for (Assessment assessment : academicClass.getAssessments()) {

                    Element assessmentElement = document.createElement("assessment");
                    assessmentsElement.appendChild(assessmentElement);

                    Element type = document.createElement("type");
                    type.setTextContent(assessment.getType());
                    assessmentElement.appendChild(type);

                    Element value = document.createElement("value");
                    value.setTextContent(String.valueOf(assessment.getValue()));
                    assessmentElement.appendChild(value);

                    Element weight = document.createElement("weight");
                    weight.setTextContent(String.valueOf(assessment.getWeight()));
                    assessmentElement.appendChild(weight);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            transformer.transform(
                    new DOMSource(document),
                    new StreamResult(new File("academic-data.xml"))
            );

            log.info("XML persistence completed successfully.");

        } catch (Exception e) {

            log.error("Failed to persist classes to XML.", e);

            throw new AcademicSystemException(
                    "Failed to save data to XML file: " + e.getMessage(),
                    e
            );
        }
    }
}