FROM openjdk:11
EXPOSE 8081
ENV DIRPATH /smart/share/lottery
COPY release/target/lottery-service-release-1.0-SNAPSHOT.jar $DIRPATH/lottery-service.jar
COPY init.sh /smart/share/init.sh
RUN chmod 755 /smart/share/init.sh
WORKDIR $DIRPATH
ENTRYPOINT ["/smart/share/init.sh"]