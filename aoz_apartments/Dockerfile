FROM openjdk:21-jdk-slim

WORKDIR /app

COPY ./mvnw ./mvnw
COPY ./.mvn ./.mvn

COPY ./pom.xml ./pom.xml
COPY ./src ./src

COPY ./target/aoz_apartments-0.0.1-SNAPSHOT.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]