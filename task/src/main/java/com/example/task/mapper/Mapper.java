package com.example.task.mapper;

import com.example.task.model.BranchDto;
import com.example.task.model.FullBranchDto;
import com.example.task.model.FullRepositoryDto;
import com.example.task.model.RepositoryDto;

import java.util.List;

public class Mapper {
    public static FullRepositoryDto map(RepositoryDto repositoryDto, List<BranchDto> branches) {
        return FullRepositoryDto
                .builder()
                .name(repositoryDto.name())
                .owner(repositoryDto.owner().login())
                .branches(branches.stream().map(Mapper::mapToBranch).toList())
                .build();
    }

    private static FullBranchDto mapToBranch(BranchDto branchDto) {
        return FullBranchDto
                .builder()
                .name(branchDto.name())
                .sha(branchDto.commit().sha())
                .build();
    }
}
