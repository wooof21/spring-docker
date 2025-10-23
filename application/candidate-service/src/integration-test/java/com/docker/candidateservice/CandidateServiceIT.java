package com.docker.candidateservice;

import com.docker.candidateservice.model.dto.CandidateDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Set;

@SpringBootTest
@AutoConfigureWebTestClient
public class CandidateServiceIT extends BaseTest {

    @Autowired
    private WebTestClient client;

    @Test
    void allCandidatesTest() {
        this.client.get()
                .uri("/candidate/all")
                .exchange()
                .expectStatus()
                .is2xxSuccessful()
                .expectBody()
                .jsonPath("$")
                .isNotEmpty();
    }

    @Test
    void getCandidateByIdTest() {
        this.client.get()
                .uri("/candidate/1")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(1)
                .jsonPath("$.name").isEqualTo("John")
                .jsonPath("$.skills.size()").isEqualTo(2)
                .jsonPath("$.recommendedJobs.size()").isEqualTo(2);
    }

    @Test
    void postCandidateTest() {
        var dto = CandidateDto.create(null, "Doe", Set.of("k8s"));
        this.client.post()
                .uri("/candidate")
                .bodyValue(dto)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.name").isEqualTo("Doe");
    }

    @Test
    void jobServiceReturns4xx() {
        this.client.get()
                .uri("/candidate/2")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(2)
                .jsonPath("$.name").isEqualTo("Jane")
                .jsonPath("$.skills.size()").isEqualTo(1)
                .jsonPath("$.recommendedJobs").isEmpty();
    }

    @Test
    void jobServiceReturns5xx() {
        this.client.get()
                .uri("/candidate/3")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.name").isEqualTo("Jake")
                .jsonPath("$.skills.size()").isEqualTo(1)
                .jsonPath("$.recommendedJobs").isEmpty();
    }
}
