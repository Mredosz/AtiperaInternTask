package com.example.task;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class RepositoryIntegrationTest {
    private static final String URL = "http://localhost:8080";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnNotForkRepositoriesWithBranches() throws Exception {
        mockMvc.perform(get(URL + "/repository"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].RepositoryName").value("JAZ-Project"))
                .andExpect(jsonPath("$[1].OwnerLogin").value("Mredosz"))
                .andExpect(jsonPath("$[1].branches[0].name").value("main"))
                .andExpect(jsonPath("$[1].branches[0].sha").value("9ad21c9425d6a0a9dce9d6d5be04bd64c450a89b"));
    }

    @Test
    void return404StatusCode() throws Exception {
        mockMvc.perform(get(URL + "/repository"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message").value("Didn't found repositories for this username"))
                .andExpect(jsonPath("status").value("404"));
    }
}
