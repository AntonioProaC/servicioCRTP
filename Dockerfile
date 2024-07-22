ARG http_proxy=http://10.33.128.80:8080
ARG https_proxy=http://10.33.128.80:8080
FROM harbor.coppel.io/library/openjdk:8u181-jre-alpine
EXPOSE 8080
#RUN apk add --no-cache tzdata && cp /usr/share/zoneinfo/America/Mazatlan /etc/localtime && echo America/Mazatlan > /etc/timezone && apk del tzdata 
WORKDIR /sysx/progs
COPY ./app.jar ./config.properties ./hibernate.cfg.xml /sysx/progs/
ENTRYPOINT ["java"]
CMD ["-XX:+UseSerialGC", "-Xss512k", "-XX:MaxRAM=256m", "-jar", "app.jar", "config.properties"]
