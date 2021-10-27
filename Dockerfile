FROM openjdk:16-alpine3.13

RUN mkdir /opt/app

COPY ./build/libs/platform_redcollar-0.0.1-SNAPSHOT.jar  /opt/app

EXPOSE 8080

CMD ["java", "-jar", "/opt/app/platform_redcollar-0.0.1-SNAPSHOT.jar"]