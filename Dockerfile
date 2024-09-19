#FROM openjdk:17-jdk-slim
#WORKDIR /app
#COPY target/StudyCycle-0.0.1-SNAPSHOT.jar  /app/StudyCycle-0.0.1-SNAPSHOT.jar
#EXPOSE 8080
#CMD ["java", "-jar", "StudyCycle-0.0.1-SNAPSHOT.jar"]

#FROM openjdk:17-jdk-slim
#EXPOSE 8080
#ADD target/studysycle-docker.jar studycycle-docker.jar
#ENTRYPOINT["java","-jar","/studycycle-docker.jar"]

# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the executable jar file into the container at /app
ADD target/studycycle-docker.jar studycycle-docker.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app/studycycle-docker.jar"]
