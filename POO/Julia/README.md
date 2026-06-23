# Julia – Testes, Logging e ClassService

Minha parte do trabalho (TUS-2385 a TUS-2396). Deixei a pasta como um projeto
Maven que compila e roda os testes sozinho, pra eu conseguir mostrar que
"funciona individualmente" antes da integração.

## O que está aqui

**ClassService (TUS-2396)**
- `service/ClassService.java` – tirei a regra de cadastro de turma do controller.
- `controller/AcademicSystemController.java` – versão enxuta só pra mostrar a delegação
  (o controller completo é do Pedro/Otávio).

**Logging (TUS-2390 a TUS-2394)** – uso `java.util.logging`, configuração separada da regra de negócio.
- `logging/LoggingConfig.java` + `resources/logging.properties` – carrega o formato (data, nível, classe, mensagem).
- `logging/AppLogger.java` – ponto único pra pegar logger.
- `logging/SecurityAuditLogger.java` – login/logout e falhas de autorização (a senha nunca entra no log).
- `logging/PersistenceAuditLogger.java` – operações de persistência.
- `logging/ReportAuditLogger.java` – geração de relatórios.

**Testes (TUS-2385 a US-2389 e TUS-2395)** – em `src/test`.
- `DomainValidationTest` – validação de domínio (Jakarta Bean Validation).
- `AuthenticationTest` / `AuthorizationTest` – segurança.
- `ReportGenerationTest` – relatórios.
- `PersistenceRepositoryTest` – TXT/XML/JSON.
- `LoggingInfrastructureTest` – infra de logging.

## Rodar os testes

```bash
mvn test
```
