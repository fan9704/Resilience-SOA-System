FROM openjdk:11-slim

LABEL desc="Basic Dockerfile"


COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]

