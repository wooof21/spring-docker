
# multi-stage Dockerfile
# docker build -t job-service -f job-service/src/main/resources/Dockerfile .

#----------------Build Stage---------------------
# to pull gralde image for build the project
FROM gradle:8.13-jdk21-corretto AS BUILDER

WORKDIR /gradle/app1

COPY build.gradle .
COPY src ./src

# Build the JAR (skip tests for faster build)
RUN gradle clean build -x test

#----------------Run Stage---------------------


FROM bellsoft/liberica-openjdk-alpine:21

WORKDIR /usr/share/app

# relative path to the JAR
#COPY job-service/build/libs/*.jar app.jar
COPY --from=BUILDER /gradle/app1/build/libs/*.jar job-app.jar

EXPOSE 8080

CMD [ "java", "-jar", "job-app.jar" ]