FROM maven:3.8.3-openjdk-17 as build

WORKDIR /app

COPY . /app

RUN mvn clean install -DskipTests

CMD ["java", "-jar", "/app/target/wallet-0.0.1-SNAPSHOT.jar"]
