# FROM maven:3.8.6-eclipse-temurin-17-focal AS build
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean package


FROM openjdk:17-alpine
COPY target/inventory-0.0.1-SNAPSHOT.jar /usr/local/lib/project.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/project.jar"]