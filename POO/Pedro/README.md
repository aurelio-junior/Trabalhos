## O que está aqui

**Assessments (US-2361)**
- `model/Assessment.java` – classe abstrata base para os quatro tipos de avaliação.
- `model/Exam.java` / `model/PracticalAssignment.java` / `model/Seminar.java` / `model/Assignment.java` – implementações concretas de cada tipo.

**Persistência TXT (TUS-2362)**
- `repository/AcademicClassRepository.java` – interface de repositório, isola o domínio de qualquer detalhe de I/O.
- `repository/TxtAcademicClassRepository.java` – grava `classCode|classTitle|assessmentType|value|weight` por linha.
- `repository/XmlAcademicClassRepository.java` / `repository/JsonAcademicClassRepository.java` – stubs prontos para expansão.
- `repository/InMemoryAcademicClassRepository.java` – delegate em memória usado por todos os repositórios de arquivo.

**Cadastro de turmas por teclado (US-2363)**
- `view/ConsoleView.java` – lê código e título, delega ao controller, exibe resultado.

**Menu de linha de comando (US-2364)**
- `view/ConsoleView.java` – menu separado por role (`ADMIN` / `PROFESSOR`); erros de input não encerram a aplicação.

**Lombok (TUS-2365)**
- `model/Assessment.java`, `model/AcademicClass.java`, `model/User.java` – `@Getter`, `@Setter`, `@EqualsAndHashCode`, `@ToString` substituem todo o boilerplate.

**Autenticação e autorização (US-2366)**
- `security/AuthenticationService.java` – autentica com base no `TxtUserRepository`; **senha nunca entra no log**.
- `repository/TxtUserRepository.java` – lê `users.txt` no formato `username|password|ROLE`.

**Exceções de domínio (US-2367)**
- `exception/AcademicSystemException.java` – superclasse de todas as exceções acadêmicas.
- `exception/InvalidClassException.java` / `exception/InvalidAssessmentException.java` / `exception/ClassNotFoundException.java`.

**Exceções de teclado (US-2368)**
- `exception/KeyboardInputException.java` – superclasse separada das exceções de domínio e segurança.
- `exception/InvalidNumericInputException.java` / `exception/InvalidMenuOptionException.java`.

**Exceções de segurança (US-2369)**
- `exception/SecurityException.java` – superclasse das exceções de autenticação e autorização.
- `exception/AuthenticationException.java` / `exception/AuthorizationException.java`.

**Refatoração do controller (TUS-2370)**
- `controller/AcademicSystemController.java` – concentra registrar turma, registrar avaliação, salvar dados e buscar por código.
- `Main.java` fica responsável apenas por startup, autenticação, exibição e navegação de menu.

**Jakarta Bean Validation (TUS-2371)**
- `validation/DomainValidator.java` – ponto único de validação; converte `ConstraintViolation` em `AcademicSystemException`.
- Anotações `@NotBlank`, `@Pattern`, `@Size`, `@DecimalMin`, `@DecimalMax` nas classes de domínio.

**Configuração de persistência (US-2372)**
- `service/PersistenceService.java` – troca o repositório ativo em runtime (`TXT` / `XML` / `JSON`).
- `controller/AcademicSystemController.java` – operação restrita a `ADMIN`; registrada no log de auditoria.

## Rodar o projeto

```bash
mvn clean compile
mvn package
java -jar target/academic-system-1.0-SNAPSHOT.jar
```

## Credenciais padrão

Caso o arquivo `users.txt` não exista, o sistema carrega dois usuários de fallback:

| Username    | Password  | Role      |
|-------------|-----------|-----------|
| `admin`     | `admin123`| ADMIN     |
| `professor` | `prof123` | PROFESSOR |

Para usar um arquivo próprio, crie `users.txt` na raiz com o formato:

```
username|password|ROLE
admin|admin123|ADMIN
professor|prof123|PROFESSOR
```
