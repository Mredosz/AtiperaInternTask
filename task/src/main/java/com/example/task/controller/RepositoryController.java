package com.example.task.controller;

import com.example.task.exception.exceptions.UserRepositoriesNotFound;
import com.example.task.model.FullRepositoryDto;
import com.example.task.service.RepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RepositoryController {
    private final RepositoryService repositoryService;

    @GetMapping("repository")
    public ResponseEntity<List<FullRepositoryDto>> get() throws UserRepositoriesNotFound {
        var repos = repositoryService.getUserRepositories("Mredosz");
        return ResponseEntity.ok(repos);
    }
}
