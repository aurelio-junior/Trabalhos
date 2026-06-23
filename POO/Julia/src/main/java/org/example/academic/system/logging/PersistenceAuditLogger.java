package org.example.academic.system.logging;

import org.example.academic.system.model.PersistenceType;

import java.util.logging.Logger;

/**
 * TUS-2393 - log das operações de persistência.
 * Fica fora do modelo de domínio (mora na camada de logging).
 */
public final class PersistenceAuditLogger {

    private static final Logger log = AppLogger.get(PersistenceAuditLogger.class);

    private PersistenceAuditLogger() {
    }

    public static void saved(PersistenceType type, String fileName) {
        log.info("Academic data saved using " + type.name() + " persistence to file '" + fileName + "'");
    }
}
