# Estágio 1: Build da aplicação usando Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia o arquivo de configuração de dependências
COPY pom.xml .
# Baixa as dependências em cache para acelerar builds futuros
RUN mvn dependency:go-offline

# Copia o código-fonte da aplicação
COPY src ./src

# Compila e gera o pacote JAR sem rodar testes unitários (para agilizar)
RUN mvn clean package -DskipTests

# Estágio 2: Ambiente de execução leve
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia o JAR gerado no estágio anterior (verifique o nome do jar gerado no seu target)
# Normalmente o Maven gera com o nome do artefactID-version.jar ou apenas app.jar se configurado
COPY --from=build /app/target/*.jar academic-system.jar

# Define o comando de entrada para iniciar o CLI
ENTRYPOINT ["java", "-jar", "academic-system.jar"]