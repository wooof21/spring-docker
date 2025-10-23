package com.docker.candidateservice;

import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public abstract class BaseTest {

    private static final Service MONGO = Service.create(
         "mongo-db", 27017, "0",
            "mongodb://usr1:pwd1@%s:%s/candidate",
            "MONGO_HOST_PORT"
    );

    private static final Service JOB = Service.create(
            "mock-server-job", 1080, "0",
            "http://%s:%s/job", "MOCK_SERVER_JOB_PORT"
    );

    @ClassRule
    public static final DockerComposeContainer<?> compose =
            new DockerComposeContainer<>(new File("../docker-compose.yaml"));

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        compose
                .withEnv(MONGO.getHostPortEnvVariable(), MONGO.getHostPort())
                .withEnv(JOB.getHostPortEnvVariable(), JOB.getHostPort())
                .withExposedService(MONGO.getName(), MONGO.getPort(), Wait.forListeningPort())
                .withExposedService(JOB.getName(), JOB.getPort(), Wait.forHttp("/health").forStatusCode(200))
                .start();
        var mongoHost = compose.getServiceHost(MONGO.getName(), MONGO.getPort());
        var mongoPort = compose.getServicePort(MONGO.getName(), MONGO.getPort());
        var jobHost = compose.getServiceHost(JOB.getName(), JOB.getPort());
        var jobPort = compose.getServicePort(JOB.getName(), JOB.getPort());

        registry.add("spring.data.mongodb.uri",
                () -> String.format(MONGO.getUri(), mongoHost, mongoPort));

        registry.add("job.service.url",
                () -> String.format(JOB.getUri(), jobHost, jobPort));
    }

}
