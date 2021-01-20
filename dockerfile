FROM openjdk:8-jdk-alpine
RUN addgroup -S demouser && adduser -S demouser -G demouser
USER demouser:demouser
ARG WAR_FILE=build/libs/*.war
WORKDIR /usr/src/app
COPY ${WAR_FILE} sezzle_sample_calculator.war
EXPOSE 8090
ENTRYPOINT ["java","-jar","sezzle_sample_calculator.war"]

