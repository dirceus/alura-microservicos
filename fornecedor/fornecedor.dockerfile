FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} fornecedor.jar 
ENTRYPOINT java -jar /fornecedor.jar

EXPOSE 8081