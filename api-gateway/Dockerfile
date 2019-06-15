# Use an OpenJDK Runtime as a parent image
FROM openjdk:8-jre-alpine
#FROM maven:3.6.0-jdk-8

# Define environment variables
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
JAVA_OPTS=""

RUN apk update

COPY default.sh /opt/default.sh
RUN chmod 755 /opt/default.sh

# Add Maintainer Info
LABEL maintainer="kalilmvp@gmail.com"

# Set the working directory to /app
WORKDIR /app

# Copy the executable into the container at /app
ADD target/*.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8340

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
