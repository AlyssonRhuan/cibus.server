# Use a imagem base do OpenJDK 8
FROM openjdk:8-jdk-alpine

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR do projeto para o contêiner
COPY target/seu-backend.jar .

# Comando para executar o aplicativo Java
CMD ["java", "-jar", "seu-backend.jar"]
