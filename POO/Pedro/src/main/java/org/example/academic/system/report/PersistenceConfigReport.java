package org.example.academic.system.report;

import org.example.academic.system.model.PersistenceType;

/**
 * Report showing the currently configured persistence type (US-2372 AC8).
 */
public class PersistenceConfigReport {

    private final PersistenceType currentType;

    public PersistenceConfigReport(PersistenceType currentType) {
        this.currentType = currentType;
    }

    public void print() {
        System.out.println("=== Persistence Configuration Report ===");
        System.out.println("Active persistence type : " + currentType);
        System.out.println("========================================");
    }
}
