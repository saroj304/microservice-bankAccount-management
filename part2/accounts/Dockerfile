#start with a base image containing java runtime
FROM openjdk:17-jdk-slim

#information around who maintains the image
# Set up maintainer information
LABEL maintainer="Saroj Khatiwada <sarojkhatiwada1999@gmail.com>"

COPY ./target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]

