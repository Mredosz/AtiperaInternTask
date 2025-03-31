package com.example.task.model;

public record RepositoryDto(String name,
                            OwnerDto owner,
                            boolean fork) {
}
