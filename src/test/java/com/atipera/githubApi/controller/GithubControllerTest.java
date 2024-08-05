package com.atipera.githubApi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GithubControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Value("${github.token}")
    private String token;

    @Test
    void shouldPrintRepositoryNames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/github/users/{userLogin}/repos", "JoannaWalach1")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].repositoryName").value("algoritms"))
                .andReturn();
    }

    @Test
    void shouldPrintBranchesNames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/github/repos/{userLogin}/{nameOfRepo}/branches", "JoannaWalach1", "School-App")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                        .header("Authorization", "Token: " + token))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].branchName").value("main"))
                .andReturn();
    }

    @Test
    void shouldThrowExceptionIfLoginIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/github/users/{userLogin}/repos", "")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("No handler found for GET /api/github/users//repos"))
                .andReturn();
    }

    @Test
    void shouldThrowExceptionIfLoginDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/github/users/{userLogin}/repos", "nonexistentUser")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                        .header(HttpHeaders.AUTHORIZATION, "token " + token))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("User 'nonexistentUser' does not exist"))
                .andReturn();
    }

    @Test
    void shouldThrowExceptionIfRepoNameIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/github/repos/{userLogin}/{nameOfRepo}/branches", "JoannaWalach1", "")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("No handler found for GET /api/github/repos/JoannaWalach1//branches"))
                .andReturn();
    }
}
