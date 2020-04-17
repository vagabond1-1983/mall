# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
MAINTAINER vaga <vagabond1_1983@163.com>

# 设置locale
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV TZ=Asia/Shanghai

# 设置jacoco
ENV md5 d4f07cb98bbf2305dbd16ee3012d7321
ENV jacoco_version 0.8.3

RUN apk update && apk add curl && apk add unzip && \
    curl -f https://repo1.maven.org/maven2/org/jacoco/jacoco/$jacoco_version/jacoco-$jacoco_version.zip -o jacoco.zip && \
    sum=$(cat jacoco.zip | md5sum | cut -d ' ' -f 1) && \
    echo $sum && \
    if [ ! $sum == $md5 ]; then exit 1; fi && \
    mkdir /jacoco && \
    unzip jacoco.zip -d /jacoco && \
    rm jacoco.zip

VOLUME "/jacoco"

RUN mkdir /app

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} /app/mall-admin.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom -javaagent:/jacoco/lib/jacocoagent.jar=includes=com.macro.mall.*,output=tcpserver,address=*", "-jar","/app/mall-admin.jar"]

