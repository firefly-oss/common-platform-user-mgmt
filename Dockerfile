FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY common-platform-user-mgmt.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]