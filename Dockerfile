# Imagen base de Java 8
FROM openjdk:8-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve

COPY src ./src
# Comando para ejecutar la aplicación Spring Boot
CMD ["./mvnw", "spring-boot:run"]
