FROM adoptopenjdk/openjdk11:jre-11.0.13_8-alpine
EXPOSE 5500
ADD target/card-transfer-0.0.1-SNAPSHOT.jar cardtransfer.jar
ENTRYPOINT ["java","-jar","/cardtransfer.jar"]