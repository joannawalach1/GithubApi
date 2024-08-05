package com.atipera.githubApi.domain;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class GithubBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Branch name must not be null")
    private String name;
    @NotNull(message = "Commit details must not be null")
    private GithubLastCommitSha commit;
}
