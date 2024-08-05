package com.atipera.githubApi.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@EqualsAndHashCode
public class BranchResponse {
    @NotEmpty(message = "Last commit SHA must not be empty")
    private String lastCommitSha;
    @NotEmpty(message = "Branch name must not be empty")
    private String branchName;
}

