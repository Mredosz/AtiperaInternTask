package com.example.task.service;

import com.example.task.exception.exceptions.UserRepositoriesNotFound;
import com.example.task.mapper.Mapper;
import com.example.task.model.BranchDto;
import com.example.task.model.FullRepositoryDto;
import com.example.task.model.RepositoryDto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class RepositoryService {
    private static final String REPO_URL = "https://api.github.com/users/";
    private static final String BRANCH_URL = "https://api.github.com/repos/";
    private final RestClient restClient;

    public RepositoryService() {
        this.restClient = RestClient.create();
    }

    public List<FullRepositoryDto> getUserRepositories(String username) throws UserRepositoriesNotFound {
        return getRepos(username).parallelStream()
                .filter(r -> !r.fork())
                .map(r -> Mapper.map(r, getBranches(username, r.name())))
                .toList();
    }

    private List<RepositoryDto> getRepos(String username) throws UserRepositoriesNotFound {
        try {
            return restClient
                    .get()
                    .uri(REPO_URL + username + "/repos")
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {});
        } catch (Exception e) {
            throw new UserRepositoriesNotFound();
        }
    }

    private List<BranchDto> getBranches(String username, String repoName){
        return restClient
                .get()
                .uri(BRANCH_URL + username +"/" + repoName + "/branches")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}
