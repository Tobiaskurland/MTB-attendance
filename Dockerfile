FROM 966879952819.dkr.ecr.eu-west-1.amazonaws.com/mtd-attend-kea:latest
COPY target/demo-0.0.1-SNAPSHOT.jar docker-server-1.0.0.jar
ENTRYPOINT ["java","-jar","/docker-server-1.0.0.jar"]