FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} loja.jar 
ENTRYPOINT java -jar /loja.jar

EXPOSE 8080