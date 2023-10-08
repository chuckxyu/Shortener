# Use a base image with Java pre-installed
FROM openjdk:11-jre-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/Shortener-0.0.1-SNAPSHOT.jar .

# Expose the port your application listens on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "Shortener-0.0.1-SNAPSHOT.jar"]
