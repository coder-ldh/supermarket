FROM openjdk:8
WORKDIR /opt
COPY ./target/*.jar app.jar


RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone