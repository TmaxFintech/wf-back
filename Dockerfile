FROM adoptopenjdk/openjdk11
COPY build/libs/*.jar app.jar
CMD ["java", "-jar", "/app.jar"]

