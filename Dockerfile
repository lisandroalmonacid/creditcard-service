FROM amazoncorretto:17-alpine-jdk
LABEL maintainer="Lisandro Almonacid"
COPY target/creditcard-service-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
