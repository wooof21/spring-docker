package com.docker.jobservice.testcontainer.compose;

import org.junit.ClassRule;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public abstract class BaseTest {

    private static final int MONGO_PORT = 27017;
    private static final String MONGO = "mongo-db";

    /**
     * Use the docker-compose.yaml to create the test container
     */
    @ClassRule
    public static final DockerComposeContainer<?> compose =
            new DockerComposeContainer<>(new File("../docker-compose.yaml"));

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        /**
         * docker-compose pass env variable: MONGO_HOST_PORT
         *  - 0 -> random port
         */
        compose
                .withEnv("MONGO_HOST_PORT", "0")
                .withExposedService(MONGO, MONGO_PORT, Wait.forListeningPort())
                .start();
        var host = compose.getServiceHost(MONGO, MONGO_PORT);
        var port = compose.getServicePort(MONGO, MONGO_PORT);

        registry.add("spring.data.mongodb.host", () -> host);
        registry.add("spring.data.mongodb.port",
                () -> port);
        registry.add("spring.data.mongodb.username", () -> "usr1");
        registry.add("spring.data.mongodb.password", () -> "pwd1");
        registry.add("spring.data.mongodb.database", () -> "job");
    }

}
