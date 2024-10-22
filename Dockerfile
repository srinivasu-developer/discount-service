# Start with a base image containing Java and Gradle
FROM gradle:7.4-jdk11 as build

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle project files
COPY build.gradle settings.gradle /app/

# Download the dependencies
RUN gradle build --no-daemon -x test

# Copy the source code
COPY src /app/src

# Build the project
RUN gradle build --no-daemon

# Now, use a lighter image to run the application
FROM openjdk:21-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/discount-service.jar

# Expose the port the app will run on
EXPOSE 3297

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/discount-service.jar"]
