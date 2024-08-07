package com.atipera.githubApi.controller;

import com.atipera.githubApi.domain.GithubRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Value("${github.token}")
    private String token;

    @Test
    void shouldGetRepos() throws Exception {
        webTestClient.mutate()
                .responseTimeout(Duration.ofSeconds(20))
                .build()
                .get().uri("/api/github/users/{userLogin}/repos", "JoannaWalach1")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(GithubRepository.class);
    }

    @Test
    void shouldThrowExceptionIfLoginIsNull() {
        webTestClient.get().uri("/api/github/users/{userLogin}/repos", "")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void shouldThrowExceptionIfLoginDoesNotExist() {
        webTestClient.get().uri("/api/github/users/{userLogin}/repos", "nonexistentUser")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    @Test
    void shouldThrowExceptionIfRepoNameIsNull() {
        webTestClient.get().uri("/api/github/repos/{userLogin}/{nameOfRepo}/branches", "JoannaWalach1", "")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }
}
