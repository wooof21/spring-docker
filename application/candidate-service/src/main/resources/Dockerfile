
# to pull gralde image for build the project
FROM gradle:8.13-jdk21-corretto AS BUILDER

WORKDIR /gradle/app2

COPY build.gradle .
COPY src ./src

# Build the JAR (skip tests for faster build)
RUN gradle clean build -x test


FROM bellsoft/liberica-openjdk-alpine:21

WORKDIR /usr/share/app

COPY --from=BUILDER /gradle/app2/build/libs/*.jar candidate-app.jar

EXPOSE 8081

CMD [ "java", "-jar", "candidate-app.jar" ]