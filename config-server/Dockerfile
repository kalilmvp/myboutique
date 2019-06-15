# Use an OpenJDK Runtime as a parent image
FROM openjdk:8-jre-alpine
#FROM maven:3.6.0-jdk-8

# Define environment variables
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
JAVA_OPTS=""

#ENV http_proxy 'http://proxy.jupiter.co.ao:3128'
#ENV https_proxy 'http://proxy.jupiter.co.ao:3128'

#RUN apk update

#COPY apt.conf /etc/apt/apt.conf
#COPY wait-for.sh /opt/wait-for.sh

# Add Maintainer Info
LABEL maintainer="kalilmvp@gmail.com"

# Set the working directory to /app
WORKDIR /app

# Copy the executable into the container at /app
ADD target/*.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8888

#ENV http_proxy ''
#ENV https_proxy ''

# Run app.jar when the container launches
#CMD ["mvn","spring-boot:run"]
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
