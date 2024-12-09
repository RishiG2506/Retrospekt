FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY src /app/src
COPY pom.xml /app

RUN mvn clean package -DskipTests

FROM openjdk:21
WORKDIR /app

COPY --from=build /app/target/LLMApp.jar /app/LLMApp.jar

VOLUME /data/h2db

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "LLMApp.jar"]