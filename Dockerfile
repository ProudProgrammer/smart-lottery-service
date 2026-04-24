FROM eclipse-temurin:25-jre-jammy

LABEL maintainer="mail.gabor.balazs@gmail.com"
LABEL description="Smart Lottery Service"

ENV APP_HOME=/app
WORKDIR $APP_HOME

RUN useradd -m appuser

COPY release/target/lottery-service-release-1.0-SNAPSHOT.jar app.jar

COPY init.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh \
    && mkdir -p /app/logs \
    && chown -R appuser:appuser /app

EXPOSE 8081

USER appuser

ENTRYPOINT ["/entrypoint.sh"]
