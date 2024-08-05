FROM openjdk:21-jdk-slim
WORKDIR /app
COPY target/github-api-0.0.1-SNAPSHOT.jar github-api.jar
ENTRYPOINT ["java", "-jar", "github-api.jar"]

