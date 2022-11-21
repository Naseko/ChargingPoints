FROM openjdk:11-jre-slim as jdkbase
FROM jdkbase

RUN apt update && apt install -y iputils-ping \
    && apt install -y nano && apt install -y curl \
    && apt install -y zip && apt install -y unzip \
    && apt install -y traceroute && apt install -y telnet

ADD target/chargepoints-0.0.1.jar /run/cpa.jar

ENTRYPOINT ["java","-jar","run/cpa.jar","-Xms128m","-Xmx4096m"]
