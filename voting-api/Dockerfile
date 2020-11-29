FROM openjdk:11-jdk-slim-buster
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=docker","/app.jar"]