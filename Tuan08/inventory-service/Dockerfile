FROM maven:3.8.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

ENV MAVEN_OPTS="-Dfile.encoding=UTF-8"
RUN mvn clean package -DskipTests \
    -Dproject.build.sourceEncoding=UTF-8 \
    -Dproject.reporting.outputEncoding=UTF-8 \
    -Dmaven.compiler.encoding=UTF-8 \
    -Dmaven.resources.encoding=UTF-8 \
    -Dfile.encoding=UTF-8

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/inventory-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-Dfile.encoding=UTF-8", "-jar", "app.jar"]