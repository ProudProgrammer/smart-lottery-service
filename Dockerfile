FROM openjdk:11
COPY release/target/lottery-service-release-1.0-SNAPSHOT.jar lottery-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","/lottery-service.jar"]