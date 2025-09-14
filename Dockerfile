# 1. Use official JDK as base image
FROM openjdk:17-jdk-slim

# 2. Set working directory
WORKDIR /app

# 3. Copy the correct jar
COPY target/task.management-0.0.1-SNAPSHOT.jar app.jar

# 4. Expose port
EXPOSE 8080

# 5. Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
