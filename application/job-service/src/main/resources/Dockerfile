
# multi-stage Dockerfile
# docker build -t job-service -f job-service/src/main/resources/Dockerfile .

#----------------Build Stage---------------------
# to pull gralde image for build the project
FROM gradle:8.13-jdk21-corretto AS BUILDER

WORKDIR /gradle/app

COPY job-service/build.gradle .
COPY job-service/src ./src

# Build the JAR (skip tests for faster build)
RUN gradle clean build -x test

#----------------Run Stage---------------------


FROM bellsoft/liberica-openjdk-alpine:21

WORKDIR /usr/share/app

# relative path to the JAR
#COPY job-service/build/libs/*.jar app.jar
COPY --from=BUILDER /gradle/app/build/libs/*.jar app.jar

EXPOSE 8080

CMD [ "java", "-jar", "app.jar" ]