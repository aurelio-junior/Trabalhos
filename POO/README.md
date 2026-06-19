# README – Trabalho Semestral

**Orientação a Objetos – Bacharelado em Ciência da Computação** 
**Apresentação:** 29 de junho de 2026 
**Professor:** Rodrigo Martins Pagliares

---

# 1. Repositório Git

O repositório do grupo já foi criado no GitHub com o repositório base do professor. Todos os integrantes devem cloná-lo antes de começar.

🔗 https://github.com/PedroBrassi/Trabalhos.git

## Como contribuir

1. Clone o repositório (ou faça fork para sua conta pessoal).
2. Crie/use a pasta com o **SEU NOME** na raiz do projeto (ex: `pedro/`, `aurelio/`, `julia/`, `otavio/`).
3. Adicione apenas os arquivos referentes às suas user stories nessa pasta.
4. Faça commits com mensagens claras, ex:
   ```bash
   feat(US-2361): cadastrar avaliações em turmas
   ```
5. Quando concluir todas as suas stories, avise o grupo para integrarmos tudo em um único arquivo de entrega.

> ⚠️ Nunca commite diretamente na pasta de outro colega. A integração final será feita em conjunto.

---

# 2. Divisão das User Stories

As 59 histórias foram divididas igualmente entre os 5 integrantes (11–12 por pessoa), equilibrando histórias funcionais (US) e técnicas (TUS), além de mesclar diferentes níveis de dificuldade.

## Visão Geral da Distribuição

| Integrante | Quantidade | Foco Temático |
|------------|------------|---------------|
| Pedro | 12 | Cadastro, menus, autenticação, exceções, configuração de persistência, Lombok e Bean Validation |
| Aurelio | 12 | XML/JSON, relatórios, menus dinâmicos, Docker, igualdade de domínio e testes iniciais |
| Julia | 12 | Testes, logging e ClassService |
| Otavio 27 | 12 | Services, simplificação do Controller, testes de Services e JavaFX Login |
| Otavio 30 | 11 | JavaFX, CI/CD, Docker automático, cobertura de testes e release workflow |

---

## 👤 Pedro (12 histórias)

| Código | Tipo | Título |
|---------|------|---------|
| US-2361 | US | Registering Assessments in Classes |
| TUS-2362 | TUS | Persist class assessments to TXT File |
| US-2363 | US | Register classes through keyboard input |
| US-2364 | US | Manage academic system through command line menu |
| US-2365 | TUS | Refactor domain model using Lombok |
| US-2366 | US | Authenticate users and authorize actions based on roles |
| US-2367 | US | Handle academic domain errors with custom exceptions |
| US-2368 | US | Handle keyboard input errors with custom exceptions |
| US-2369 | US | Handle authentication and authorization errors |
| TUS-2370 | TUS | Refactor menu operations into AcademicSystemController |
| TUS-2371 | TUS | Validate academic domain objects using Jakarta Bean Validation |
| US-2372 | US | Configure persistence type as administrator |

---

## 👤 Aurelio (12 histórias)

| Código | Tipo | Título |
|---------|------|---------|
| US-2373 | US | Save academic data to XML file |
| US-2374 | US | Save academic data to JSON file |
| US-2375 | US | Generate class assessment summary report |
| US-2376 | US | Generate assessment weight report |
| US-2377 | US | Generate persistence configuration report |
| US-2378 | US | Role-based dynamic menu rendering |
| US-2379 | US | Logout |
| US-2380 | US | Display role-specific sequential menus |
| US-2381 | US | Deliver academic system with Docker |
| TUS-2382 | TUS | Define equality for identifiable domain objects |
| TUS-2383 | TUS | Configure automated testing infrastructure |
| TUS-2384 | TUS | Test identifiable domain object equality |

---

## 👤 Julia (12 histórias)

| Código | Tipo | Título |
|---------|------|---------|
| TUS-2385 | TUS | Test academic domain validation |
| S-2386 | US | Test authentication behavior |
| US-2387 | US | Test authorization behavior |
| US-2388 | US | Test report generation |
| US-2389 | US | Test persistence repositories |
| TUS-2390 | TUS | Configure application logging infrastructure |
| TUS-2391 | TUS | Log authentication and logout events |
| TUS-2392 | TUS | Log authorization failures |
| TUS-2393 | TUS | Log persistence operations |
| TUS-2394 | TUS | Log report generation |
| TUS-2395 | TUS | Verify logging infrastructure behavior |
| TUS-2396 | TUS | Introduce ClassService |

---

## 👤 Otavio 27 (12 histórias)

| Código | Tipo | Título |
|---------|------|---------|
| TUS-2397 | TUS | Introduce AssessmentService |
| TUS-2398 | TUS | Introduce PersistenceService |
| TUS-2399 | TUS | Introduce ReportService |
| TUS-2400 | TUS | Simplify AcademicSystemController |
| TUS-2401 | TUS | Test ClassService behavior |
| TUS-2402 | TUS | Test AssessmentService behavior |
| TUS-2403 | TUS | Test PersistenceService behavior |
| TUS-2404 | TUS | Test ReportService behavior |
| TUS-2405 | TUS | Test AcademicSystemController delegation behavior |
| TUS-2406 | TUS | Configure JavaFX application infrastructure |
| TUS-2407 | TUS | Create JavaFX login screen |
| TUS-2414 | TUS | Introduce AuthenticationController for JavaFX (Refactoring) |

---

## 👤 Otavio 30 (11 histórias)

| Código | Tipo | Título |
|---------|------|---------|
| TUS-2409 | TUS | Create JavaFX class registration screen |
| TUS-2408 | TUS | Create JavaFX role-based main screen |
| US-2410 | US | Create JavaFX assessment registration screen |
| TUS-2411 | TUS | Create JavaFX report screen |
| TUS-2412 | TUS | Create JavaFX persistence configuration screen |
| TUS-2413 | TUS | Create JavaFX class and assessment visualization screen |
| TUS-2415 | TUS | Configure CI pipeline with GitHub Actions |
| TUS-2416 | TUS | Generate test coverage reports |
| TUS-2417 | TUS | Publish Docker image automatically |
| TUS-2418 | TUS | Configure pull request validation workflow |
| TUS-2419 | TUS | Configure release workflow |

---

# 3. Checklist de Progresso

Marque cada item conforme o trabalho avança e realize commit da versão mais recente.

## 👤 Pedro – US-2361 a US-2372

- [✅] US-2361 – Registering Assessments in Classes
- [✅] TUS-2362 – Persist class assessments to TXT File
- [✅] US-2363 – Register classes through keyboard input
- [ ] US-2364 – Manage academic system through command line menu
- [ ] TUS-2365 – Refactor domain model using Lombok
- [ ] US-2366 – Authenticate users and authorize actions based on roles
- [ ] US-2367 – Handle academic domain errors with custom exceptions
- [ ] US-2368 – Handle keyboard input errors with custom exceptions
- [ ] US-2369 – Handle authentication and authorization errors
- [ ] TUS-2370 – Refactor menu operations into AcademicSystemController
- [ ] TUS-2371 – Validate academic domain objects using Jakarta Bean Validation
- [ ] US-2372 – Configure persistence type as administrator

## 👤 Aurelio – US-2373 a TUS-2384

- [ ] US-2373 – Save academic data to XML file
- [ ] US-2374 – Save academic data to JSON file
- [ ] US-2375 – Generate class assessment summary report
- [ ] US-2376 – Generate assessment weight report
- [ ] US-2377 – Generate persistence configuration report
- [ ] US-2378 – Role-based dynamic menu rendering
- [ ] US-2379 – Logout
- [ ] US-2380 – Display role-specific sequential menus
- [ ] US-2381 – Deliver academic system with Docker
- [ ] TUS-2382 – Define equality for identifiable domain objects
- [ ] TUS-2383 – Configure automated testing infrastructure
- [ ] TUS-2384 – Test identifiable domain object equality

## 👤 Julia – TUS-2385 a TUS-2396

- [ ] TUS-2385 – Test academic domain validation
- [ ] S-2386 – Test authentication behavior
- [ ] US-2387 – Test authorization behavior
- [ ] US-2388 – Test report generation
- [ ] US-2389 – Test persistence repositories
- [ ] TUS-2390 – Configure application logging infrastructure
- [ ] TUS-2391 – Log authentication and logout events
- [ ] TUS-2392 – Log authorization failures
- [ ] TUS-2393 – Log persistence operations
- [ ] TUS-2394 – Log report generation
- [ ] TUS-2395 – Verify logging infrastructure behavior
- [ ] TUS-2396 – Introduce ClassService

## 👤 Otavio 27 – TUS-2397 a TUS-2414

- [ ] TUS-2397 – Introduce AssessmentService
- [ ] TUS-2398 – Introduce PersistenceService
- [ ] TUS-2399 – Introduce ReportService
- [ ] TUS-2400 – Simplify AcademicSystemController
- [ ] TUS-2401 – Test ClassService behavior
- [ ] TUS-2402 – Test AssessmentService behavior
- [ ] TUS-2403 – Test PersistenceService behavior
- [ ] TUS-2404 – Test ReportService behavior
- [ ] TUS-2405 – Test AcademicSystemController delegation behavior
- [ ] TUS-2406 – Configure JavaFX application infrastructure
- [ ] TUS-2407 – Create JavaFX login screen
- [ ] TUS-2414 – Introduce AuthenticationController for JavaFX (Refactoring)

## 👤 Otavio 30 – TUS-2409 a TUS-2419

- [ ] TUS-2409 – Create JavaFX class registration screen
- [ ] TUS-2408 – Create JavaFX role-based main screen
- [ ] US-2410 – Create JavaFX assessment registration screen
- [ ] TUS-2411 – Create JavaFX report screen
- [ ] TUS-2412 – Create JavaFX persistence configuration screen
- [ ] TUS-2413 – Create JavaFX class and assessment visualization screen
- [ ] TUS-2415 – Configure CI pipeline with GitHub Actions
- [ ] TUS-2416 – Generate test coverage reports
- [ ] TUS-2417 – Publish Docker image automatically
- [ ] TUS-2418 – Configure pull request validation workflow
- [ ] TUS-2419 – Configure release workflow

---

# 🔗 Integração e Entrega Final (Todos)

- [ ] Todos confirmaram que suas partes funcionam individualmente
- [ ] Código integrado em um único projeto
- [ ] Testes gerais rodando sem erros
- [ ] Revisão final do código-fonte realizada
- [ ] Apresentação preparada
- [ ] Arquivo de entrega gerado e verificado
- [ ] Entregue/apresentado em 29/06/2026 no Lab-B207

---

**Grupo:** Pedro | Aurelio | Julia | Otavio 27 | Otavio 30 
**Professor:** Rodrigo Martins Pagliares 
**Data da apresentação:** 29/06/2026
