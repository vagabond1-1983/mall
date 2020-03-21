# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine
FROM ictu/jacoco-agent-docker:0.8.0

# Add Maintainer Info
MAINTAINER vaga <vagabond1_1983@163.com>

# 设置locale
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV TZ=Asia/Shanghai

RUN mkdir /app

WORKDIR /app

ARG JAR_FILE

COPY target/${JAR_FILE} /app/mall-admin.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom -javaagent:/jacoco/lib/jacocoagent.jar=excludes=*_javassit_*:javax.xml.soap.*:oasis.*,output=tcpserver,address=*", "-jar","/app/mall-admin.jar"]

