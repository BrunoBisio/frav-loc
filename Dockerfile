FROM maven:3.6.1-jdk-11-slim

WORKDIR /app

COPY . /app

RUN mvn -v
RUN mvn clean install -DskipTests
EXPOSE 8080
ADD ./target/fravega-0.0.1-SNAPSHOT.jar fravega-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","fravega-0.0.1-SNAPSHOT.jar"]