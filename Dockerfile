FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/StudyCycle-0.0.1-SNAPSHOT.jar  /app/StudyCycle-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "StudyCycle-0.0.1-SNAPSHOT.jar"]