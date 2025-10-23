package com.docker.jobservice;

import com.docker.jobservice.model.dto.JobDto;
import com.docker.jobservice.testcontainer.compose.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
public class JobServiceIT extends BaseTest {

    @Autowired
    private WebTestClient client;


    @Test
    public void allJobsTest() {
        this.client.get()
                .uri("/job/all")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                // can access the json field and verify
//                .jsonPath("$.name")
//                .isEqualTo("John")
                .jsonPath("$")
                .isNotEmpty();
    }

    @Test
    public void searchBySkillsTest() {
        this.client.get()
                .uri("/job/search?skills=java")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.size()")
                .isEqualTo(3);
    }

    @Test
    public void postJobTest() {
        var dto = JobDto.create(null,
                "docker engr",
                "google",
                Set.of("docker"),
                200000, true);
        this.client.post()
                .uri("/job")
                .bodyValue(dto)
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id")
                .isNotEmpty()
                .jsonPath("$.description")
                .isEqualTo("docker engr");
    }
}
