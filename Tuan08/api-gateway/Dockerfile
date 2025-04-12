FROM maven:3.8.4-openjdk-11 AS build
WORKDIR /app
COPY pom.xml .
# Tải trước các dependencies để tận dụng cache của Docker
RUN mvn dependency:go-offline -B

COPY src ./src
# Tăng timeout và số lần thử lại
RUN mvn clean package -DskipTests --no-transfer-progress -Dmaven.wagon.http.retryHandler.count=3 -Dmaven.wagon.http.pool=false -Dmaven.wagon.httpconnectionManager.ttlSeconds=120

FROM openjdk:11-jdk-slim
WORKDIR /app
COPY --from=build /app/target/api-gateway-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
