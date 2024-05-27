# Use a imagem base do JDK 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR gerado para o diretório de trabalho do contêiner
COPY target/fastfood-0.0.1-SNAPSHOT.jar app.jar

# Define a variável de ambiente para a porta do Spring Boot
ENV PORT 8080

# Expõe a porta 8080 para o host
EXPOSE 8080

# Comando para executar o JAR
ENTRYPOINT ["java", "-jar", "app.jar"]