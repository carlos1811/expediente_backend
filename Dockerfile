FROM openjdk:8-jdk-alpine
VOLUME /tmp
EXPOSE 8008
ARG JAR_FILE=./target/spring-boot-backend-apirest-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]