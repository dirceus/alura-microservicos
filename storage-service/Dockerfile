FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package -DskipTests -Pdocker

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/*.jar /app/storage.jar
RUN mkdir /opt/storage_service
RUN chmod 755 /opt/storage_service
VOLUME /home/dirceu/dev/docker_volumes/storage_service /opt/storage_service
ENTRYPOINT java -jar /app/storage.jar

EXPOSE 8083