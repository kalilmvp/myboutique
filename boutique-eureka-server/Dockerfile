# Use an OpenJDK Runtime as a parent image
FROM openjdk:8-jre-alpine

# Define environment variables
ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
JAVA_OPTS=""

RUN apk update

COPY eureka-server.sh /opt/eureka-server.sh
RUN chmod 755 /opt/eureka-server.sh

# Add Maintainer Info
LABEL maintainer="kalilmvp@gmail.com"

# Set the working directory to /app
WORKDIR /app

# Copy the executable into the container at /app
ADD target/*.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8761

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]
