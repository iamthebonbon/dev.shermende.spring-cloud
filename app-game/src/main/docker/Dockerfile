FROM amazoncorretto:8-alpine3.18-jre
ENV JAVA_OPTS=""
COPY target/*-application.jar application.jar
ENTRYPOINT java ${JAVA_OPTS} -jar /application.jar
