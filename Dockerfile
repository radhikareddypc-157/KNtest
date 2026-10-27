FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean compile

FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/classes /app
CMD ["java", "GreatestOfThree"]
