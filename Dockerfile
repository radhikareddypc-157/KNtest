FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN javac GreatestOfThree.java TestGreatest.java
CMD ["java", "TestGreatest"]
