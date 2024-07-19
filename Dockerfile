# Use an official Maven image with JDK 22
FROM maven:3.9.8-eclipse-temurin-22 AS build

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project descriptor files
COPY pom.xml .

# Copy the rest of the source code
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Use AdoptOpenJDK 22 image for runtime
FROM openjdk:22-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the port that the app runs on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]
