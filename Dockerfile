FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# This copies whichever jar file is generated in target/ automatically
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
