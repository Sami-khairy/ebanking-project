# Stage 1: Build the application
FROM maven:3.9.1-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Config BDD
ENV SPRING_DATASOURCE_URL=jdbc:postgresql://ebanking-postgres:5432/ebanking
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=postgres

# Secret JWT
ENV JWT_SECRET=8970B93C8970B93C8970B93C8970B93C8970B93C8970B93C8970B93C8970B93C

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]