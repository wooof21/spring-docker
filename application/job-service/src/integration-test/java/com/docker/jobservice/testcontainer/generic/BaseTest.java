package com.docker.jobservice.testcontainer.generic;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.BindMode;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

/**
 * Test Containers:
 *  use test containers to run the docker services for integration test
 */
@Testcontainers
public abstract class BaseTest {


    private static final int MONGO_PORT = 27017;
    private static final String INIT_JS = "/docker-entrypoint-initdb.d/init.js";

    @Container
    private static final GenericContainer<?> mongo =
            new GenericContainer(DockerImageName.parse("mongo"))
            .withExposedPorts(MONGO_PORT)
            /**
             * if the file is located in the src/test/resources folder, use:
             *
             * .withClasspathResourceMapping("<file_path>",
             *                               INIT_JS,
             *                               BindMode.READ_ONLY)
             */
            .withFileSystemBind(
                    "../mongo-init/init.js", // relative path from job-service directory
                    INIT_JS,
                    BindMode.READ_ONLY
            )
            .waitingFor(Wait.forListeningPort());

    /**
     * The Test Container(mongo) started and will be assigned with random host and ports
     *
     * DynamicPropertySource allows to dynamically set or override application properties
     * at test runtime
     *  - it is a temporary property source for the tests
     *  - Each registry.add("property.name", supplier) call adds a property key/value
     *  - These properties behave as the properties in the `application.yml`,
     *      but only for the test context
     */
    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        mongo.start();
        registry.add("spring.data.mongodb.host", () -> mongo.getHost());
        registry.add("spring.data.mongodb.port",
                () -> mongo.getMappedPort(27017));
        registry.add("spring.data.mongodb.username", () -> "usr1");
        registry.add("spring.data.mongodb.password", () -> "pwd1");
        registry.add("spring.data.mongodb.database", () -> "job");
    }
}
