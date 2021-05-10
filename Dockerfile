FROM openjdk:8-jdk-alpine
COPY target/demo-0.0.1-SNAPSHOT.jar docker-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/docker-server-1.0.0.jar"]