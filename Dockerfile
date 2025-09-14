# 1. Use official JDK as base image
FROM openjdk:17-jdk-slim

# 2. Set working directory
WORKDIR /app

# 3. Copy jar file into image
COPY target/task.management-0.0.1-SNAPSHOT.jar.original app.jar

# 4. Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
